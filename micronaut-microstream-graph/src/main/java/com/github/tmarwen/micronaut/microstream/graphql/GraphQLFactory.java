package com.github.tmarwen.micronaut.microstream.graphql;

import com.github.tmarwen.micronaut.microstream.annotation.Root;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.FieldCoordinates;
import graphql.schema.GraphQLCodeRegistry;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.idl.RuntimeWiring;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanIntrospector;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.core.type.Argument;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static graphql.schema.GraphQLCodeRegistry.newCodeRegistry;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * GraphQL bean factory providing schema introspection based on MicroStream graph.
 *
 * @since 1.0.0
 */
@Factory
public class GraphQLFactory {

    /**
     * Share factory logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLFactory.class);

    @Bean
    @Singleton
    public GraphQL graphQL(EmbeddedStorageManager storageManager) {
        // Process types introspections
        final Set<Class<?>> processedTypes = new HashSet<>();
        // Create the runtime wiring builder and pass it through
        RuntimeWiring.Builder runtimeWiringBuilder = RuntimeWiring.newRuntimeWiring();
        GraphQLObjectType.Builder queryBuilder = newObject().name("Query");
        GraphQLCodeRegistry.Builder codeRegistryBuilder = newCodeRegistry();
        BeanIntrospector.SHARED.findIntrospections(Root.class)
                .stream()
                .findFirst()
                .ifPresent(rootIntrospection ->
                        processType(
                                rootIntrospection,
                                queryBuilder,
                                codeRegistryBuilder,
                                processedTypes,
                                storageManager
                        )
                );
        // Create the executable schema with root query builder
        GraphQLSchema graphQLSchema = GraphQLSchema.newSchema()
                .query(queryBuilder)
                .codeRegistry(codeRegistryBuilder.build())
                .build();
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * Processes the given {@code introspection} to the respective GraphQL type.
     *
     * @param introspection       the bean introspection
     * @param typeBuilder         the GraphQL type builder
     * @param codeRegistryBuilder the GraphQL runtime wiring builder
     * @param processedTypes      a caching set of all processed types
     */
    private void processType(BeanIntrospection<Object> introspection,
                             GraphQLObjectType.Builder typeBuilder,
                             GraphQLCodeRegistry.Builder codeRegistryBuilder,
                             Set<Class<?>> processedTypes,
                             EmbeddedStorageManager storageManager) {
        final Class<?> beanType = introspection.getBeanType();
        if (processedTypes.contains(beanType)) {
            return;
        }
        // avoid infinite loops
        processedTypes.add(beanType);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Processing {}", beanType);
        }
        // process type
        introspection.getBeanProperties()
                .forEach(beanProperty -> {
                            // retrieve element type if the field is an iterable
                            Class<Object> elementType;
                            final boolean isArray;
                            String propertyName = beanProperty.getName();
                            if (beanProperty.asArgument().isContainerType()) {
                                elementType = (Class<Object>) beanProperty.asArgument().getFirstTypeVariable()
                                        .map(Argument::getType)
                                        .orElseThrow(() -> new IllegalStateException(
                                                "Cannot retrieve argument type for field ["
                                                        + propertyName + "] within [" + beanType + "]")
                                        );
                                isArray = true;
                            } else {
                                elementType = beanProperty.getType();
                                isArray = false;
                            }
                            // Process if the current field type if it is an introspected type
                            BeanIntrospector.SHARED.findIntrospection(elementType)
                                    .ifPresentOrElse(propertyIntrospection -> {
                                                GraphQLOutputType elementGraphQLType;
                                                if (processedTypes.contains(elementType)) {
                                                    elementGraphQLType =
                                                            GraphQLTypeReference.typeRef(asTypeName(elementType));
                                                } else {
                                                    GraphQLObjectType.Builder propertyTypeBuilder = newObject()
                                                            .name(asTypeName(elementType));
                                                    processType(
                                                            propertyIntrospection,
                                                            propertyTypeBuilder,
                                                            codeRegistryBuilder,
                                                            processedTypes,
                                                            storageManager
                                                    );
                                                    elementGraphQLType = propertyTypeBuilder.build();

                                                }
                                                typeBuilder.field(
                                                        newFieldDefinition().name(propertyName).type(
                                                                isArray ? GraphQLList.list(elementGraphQLType)
                                                                        : elementGraphQLType
                                                        )
                                                );
                                            },
                                            () ->
                                                    typeBuilder.field(
                                                            newFieldDefinition()
                                                                    .name(propertyName)
                                                                    .type(
                                                                            toScalarType(
                                                                                    propertyName,
                                                                                    beanProperty.getType(),
                                                                                    isArray)
                                                                    )
                                                    )

                                    );
                            // Add data fetcher for field
                    if (introspection.hasStereotype(Root.class)) {
                        codeRegistryBuilder.dataFetcher(
                                FieldCoordinates.coordinates("Query", propertyName),
                                (DataFetcher<Object>) environment -> beanProperty.get(storageManager.root())
                        );
                    }
                            codeRegistryBuilder.dataFetcher(
                                    FieldCoordinates.coordinates(asTypeName(beanType), propertyName),
                                    (DataFetcher<?>) environment -> beanProperty.get(environment.getSource())
                            );
                        }
                );
    }

    /**
     * Converts the bean type to the appropriate GraphQL schema type name.
     *
     * @param beanType the bean type
     * @return the schema type name
     */
    private String asTypeName(Class<?> beanType) {
        return beanType.getSimpleName();
    }

    /**
     * Converts a JVM type to its GraphQL appropriate type.
     *
     * @param name    the field name
     * @param type    the field JVM type
     * @param isArray if the field is an array
     * @return the GraphQL scalar type
     */
    private GraphQLOutputType toScalarType(final String name, final Class<?> type, boolean isArray) {
        final GraphQLOutputType elementType;
        if ("id".equals(name)) {
            elementType = Scalars.GraphQLID;
        } else if (Boolean.class == type || boolean.class == type) {
            elementType = Scalars.GraphQLBoolean;
        } else if (Byte.class == type || byte.class == type) {
            elementType = Scalars.GraphQLByte;
        } else if (Integer.class == type || int.class == type) {
            elementType = Scalars.GraphQLInt;
        } else if (Short.class == type || short.class == type) {
            elementType = Scalars.GraphQLShort;
        } else if (BigInteger.class == type) {
            elementType = Scalars.GraphQLBigInteger;
        } else if (BigDecimal.class == type) {
            elementType = Scalars.GraphQLBigDecimal;
        } else if (Double.class == type || Float.class == type || float.class == type) {
            elementType = Scalars.GraphQLFloat;
        } else if (Character.class == type || char.class == type) {
            elementType = Scalars.GraphQLChar;
        } else {
            elementType = Scalars.GraphQLString;
        }
        // wrap in a list if it's a container type
        if (isArray) {
            return GraphQLList.list(elementType);
        } else {
            return elementType;
        }
    }
}
