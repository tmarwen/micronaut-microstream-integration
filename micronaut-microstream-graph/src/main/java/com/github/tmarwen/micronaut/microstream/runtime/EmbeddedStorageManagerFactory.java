package com.github.tmarwen.micronaut.microstream.runtime;

import com.github.tmarwen.micronaut.microstream.annotation.Root;
import com.github.tmarwen.micronaut.microstream.annotation.StorageManager;
import io.micronaut.aop.InvocationContext;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.Qualifier;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanIntrospector;
import io.micronaut.core.type.Argument;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.qualifiers.Qualifiers;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.tmarwen.micronaut.microstream.annotation.StorageManager.ROOT_MEMBER;

/**
 * MicroStreams {@link EmbeddedStorageManager} factory.
 *
 * <p>This factory represents the main entry point for MicroStream data storage
 * configuration and usually does not require any other configuration injection by the
 * user.
 *
 * @since 1.0.0
 */
@Factory
public class EmbeddedStorageManagerFactory {

    /**
     * The shared logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedStorageManagerFactory.class);

    @Bean(preDestroy = "shutdown")
    @Singleton
    public EmbeddedStorageManager embeddedStorageManager() {
        final Class<?> rootType = getRootType();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MicroStream @Root entity {} retrieved successfully", rootType);
        }
        BeanIntrospection<?> rootIntrospection = BeanIntrospection.getIntrospection(rootType);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating 'EmbeddedStorageManager' for configured root type");
        }
        return EmbeddedStorage.start(rootIntrospection.instantiate());
    }

    /**
     * Resolves and returns the root entity type for the MicroStream graph.
     *
     * @return the type of the graph root entity
     */
    private Class<?> getRootType() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Resolving MicroStream graph root type");
        }
        return BeanIntrospector.SHARED.findIntrospections(Root.class)
                .stream()
                .findFirst() // There should be only one @Root at this point
                .map(BeanIntrospection::getBeanType)
                .orElseThrow(() -> new IllegalStateException("Cannot resolve MicroStream root type." +
                        " Make sure to mark '@Root' type and have annotation processing activated at compile time.")
                );
    }
}
