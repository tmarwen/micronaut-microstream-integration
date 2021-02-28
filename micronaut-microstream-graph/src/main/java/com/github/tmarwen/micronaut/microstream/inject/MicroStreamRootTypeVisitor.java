package com.github.tmarwen.micronaut.microstream.inject;

import com.github.tmarwen.micronaut.microstream.annotation.Root;
import io.micronaut.inject.ast.ClassElement;
import io.micronaut.inject.beans.visitor.IntrospectedTypeElementVisitor;
import io.micronaut.inject.visitor.TypeElementVisitor;
import io.micronaut.inject.visitor.VisitorContext;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@link TypeElementVisitor} that marks MicroStream graph types for bean introspection.
 *
 * @since 1.0.0
 */
public class MicroStreamRootTypeVisitor implements TypeElementVisitor<Root, Object> {

    /**
     * A list of Java wrapper types.
     */
    private static final List<String> WRAPPER_TYPES = new ArrayList<>() {{
        this.add(Void.class.getName());
        this.add(Boolean.class.getName());
        this.add(Byte.class.getName());
        this.add(Character.class.getName());
        this.add(Short.class.getName());
        this.add(Integer.class.getName());
        this.add(Long.class.getName());
        this.add(Float.class.getName());
        this.add(Double.class.getName());
    }};

    /**
     * A flag marking root type processing state.
     */
    private boolean marked;

    /**
     * Cache for processed types.
     */
    private final Map<String, ClassElement> writtenTypes = new ConcurrentHashMap<>();

    @Override
    public void start(VisitorContext visitorContext) {
        this.marked = false;
    }

    @Override
    public void visitClass(ClassElement element, VisitorContext context) {
        if (!marked) {
            context.info("Marking MicroStream graph types for introspection. Root: [" + element.getName() + "]");
            visitClassElement(element, context);
            context.info("MicroStream graph types marked for introspection");
            marked = true;
        } else {
            context.fail("Only one @Root type is allowed.", element);
        }
    }

    /**
     * Visits the given {@code element} and all its bean properties recursively for
     * introspection generation.
     *
     * @param element the declaring parent type
     * @param context the visitor context
     */
    private void visitClassElement(ClassElement element, VisitorContext context) {
        // element already processed (or being processed)
        if (element == null || writtenTypes.containsKey(element.getName())) {
            return;
        }
        // mark the type as written to avoid infinite loops
        writtenTypes.put(element.getName(), element);
        // visit the root type first
        IntrospectedTypeElementVisitor introspectedTypeElementVisitor = new IntrospectedTypeElementVisitor();
        introspectedTypeElementVisitor.visitClass(new IntrospectedDelegatingClassElement(element), context);
        // recurse through all bean properties
        element.getBeanProperties().forEach(propertyElement -> {
            ClassElement type = propertyElement.getType();
            ClassElement concreteType;
            if (type.isIterable()) {
                concreteType = type.getFirstTypeArgument().orElse(null);
            } else {
                concreteType = type;
            }
            if (isBean(concreteType)) {
                visitClassElement(concreteType, context);
            }
        });
        introspectedTypeElementVisitor.finish(context);
    }

    /**
     * Returns whether the given type is considered a POJO bean.
     *
     * @param type the type to check
     * @return {@code true} if the given type is a POJO
     */
    private boolean isBean(final ClassElement type) {
        return !isPrimitiveOrWrapper(type)
                && !isString(type)
                && !isTemporal(type)
                && !Object.class.getName().equals(type.getName());
    }

    /**
     * Returns whether the given type is a char sequence type.
     *
     * @param type the type to check
     * @return {@code true} if the given type is a char sequence type
     */
    private boolean isString(final ClassElement type) {
        try {
            return CharSequence.class.isAssignableFrom(Class.forName(type.getName()));
        } catch (ClassNotFoundException e) {
            // Nothing we can do here, assume not a match
            return false;
        }
    }

    /**
     * Returns whether the given type is a primitive type.
     *
     * @param type the type to check
     * @return {@code true} if the given type is a primitive type
     */
    private boolean isPrimitiveOrWrapper(final ClassElement type) {
        return type.isPrimitive() || WRAPPER_TYPES.contains(type.getName());
    }

    /**
     * Returns whether the given type is a temporal field.
     *
     * @param type the type to check
     * @return {@code true} if the given type is a temporal type
     */
    private boolean isTemporal(final ClassElement type) {
        try {
            return Temporal.class.isAssignableFrom(Class.forName(type.getName()));
        } catch (ClassNotFoundException e) {
            // Nothing we can do here, assume not a match
            return false;
        }
    }

    @Override
    public Set<String> getSupportedAnnotationNames() {
        return Collections.singleton(Root.class.getName());
    }

    @Override
    public int getOrder() {
        return -50;
    }
}
