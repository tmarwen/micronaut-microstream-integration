package com.github.tmarwen.micronaut.microstream.inject;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.*;
import io.micronaut.core.type.Argument;
import io.micronaut.core.value.OptionalValues;
import io.micronaut.inject.ast.*;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A {@code ClassElement} implementation that delegates most operations to a
 * wrapped {@code ClassElement} and forces the {@link io.micronaut.core.annotation.Introspected}
 * annotation data retrieval.
 *
 * @since 1.0.0
 */
public final class IntrospectedDelegatingClassElement implements ClassElement {

    /**
     * The concrete wrapped class element being processed.
     */
    private final ClassElement delegate;

    public IntrospectedDelegatingClassElement(@Nonnull final ClassElement delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isAssignable(String type) {
        return delegate.isAssignable(type);
    }

    @Override
    public boolean isAssignable(ClassElement type) {
        return delegate.isAssignable(type);
    }

    @Override
    public boolean isOptional() {
        return delegate.isOptional();
    }

    @Override
    public String getCanonicalName() {
        return delegate.getCanonicalName();
    }

    @Override
    public boolean isRecord() {
        return delegate.isRecord();
    }

    @Override
    public boolean isInner() {
        return delegate.isInner();
    }

    @Override
    public boolean isEnum() {
        return delegate.isEnum();
    }

    @Override
    @NonNull
    public Optional<MethodElement> getPrimaryConstructor() {
        return delegate.getPrimaryConstructor();
    }

    @Override
    @NonNull
    public Optional<MethodElement> getDefaultConstructor() {
        return delegate.getDefaultConstructor();
    }

    @Override
    public Optional<ClassElement> getSuperType() {
        return delegate.getSuperType();
    }

    @Override
    @NonNull
    public ClassElement getType() {
        return delegate.getType();
    }

    @Override
    public String getSimpleName() {
        return delegate.getSimpleName();
    }

    @Override
    public String getPackageName() {
        return delegate.getPackageName();
    }

    @Override
    public List<PropertyElement> getBeanProperties() {
        return delegate.getBeanProperties();
    }

    @Override
    public List<FieldElement> getFields() {
        return delegate.getFields();
    }

    @Override
    @Deprecated
    public List<FieldElement> getFields(@NonNull Predicate<Set<ElementModifier>> modifierFilter) {
        return delegate.getFields(modifierFilter);
    }

    @Override
    public <T extends Element> List<T> getEnclosedElements(@NonNull ElementQuery<T> query) {
        return delegate.getEnclosedElements(query);
    }

    @Override
    public <T extends Element> Optional<T> getEnclosedElement(@NonNull ElementQuery<T> query) {
        return delegate.getEnclosedElement(query);
    }

    @Override
    public boolean isInterface() {
        return delegate.isInterface();
    }

    @Override
    public boolean isIterable() {
        return delegate.isIterable();
    }

    @Override
    @NonNull
    public Map<String, ClassElement> getTypeArguments(@NonNull String type) {
        return delegate.getTypeArguments(type);
    }

    @Override
    @NonNull
    public Map<String, ClassElement> getTypeArguments(@NonNull Class<?> type) {
        return delegate.getTypeArguments(type);
    }

    @Override
    @NonNull
    public Map<String, ClassElement> getTypeArguments() {
        return delegate.getTypeArguments();
    }

    @Override
    @NonNull
    public Map<String, Map<String, ClassElement>> getAllTypeArguments() {
        return delegate.getAllTypeArguments();
    }

    @Override
    public Optional<ClassElement> getFirstTypeArgument() {
        return delegate.getFirstTypeArgument();
    }

    @Override
    public boolean isAssignable(Class<?> type) {
        return delegate.isAssignable(type);
    }

    @Override
    public ClassElement toArray() {
        return delegate.toArray();
    }

    @Override
    public ClassElement fromArray() {
        return delegate.fromArray();
    }

    public static ClassElement of(Class<?> type) {
        return ClassElement.of(type);
    }

    @Internal
    public static ClassElement of(String typeName) {
        return ClassElement.of(typeName);
    }

    @Override
    @NonNull
    public ClassElement getGenericType() {
        return delegate.getGenericType();
    }

    @Override
    public boolean isPrimitive() {
        return delegate.isPrimitive();
    }

    @Override
    public boolean isArray() {
        return delegate.isArray();
    }

    @Override
    public int getArrayDimensions() {
        return delegate.getArrayDimensions();
    }

    @Override
    @NonNull
    public String getName() {
        return delegate.getName();
    }

    @Override
    public boolean isPackagePrivate() {
        return delegate.isPackagePrivate();
    }

    @Override
    public boolean isProtected() {
        return delegate.isProtected();
    }

    @Override
    public boolean isPublic() {
        return delegate.isPublic();
    }

    @Override
    @NonNull
    public Object getNativeType() {
        return delegate.getNativeType();
    }

    @Override
    @NonNull
    public <T extends Annotation> Element annotate(@NonNull String annotationType, @NonNull Consumer<AnnotationValueBuilder<T>> consumer) {
        return delegate.annotate(annotationType, consumer);
    }

    @Override
    @NonNull
    public Element annotate(@NonNull String annotationType) {
        return delegate.annotate(annotationType);
    }

    @Override
    @NonNull
    public <T extends Annotation> Element annotate(@NonNull Class<T> annotationType, @NonNull Consumer<AnnotationValueBuilder<T>> consumer) {
        return delegate.annotate(annotationType, consumer);
    }

    @Override
    @NonNull
    public <T extends Annotation> Element annotate(@NonNull Class<T> annotationType) {
        return delegate.annotate(annotationType);
    }

    @Override
    public boolean isAbstract() {
        return delegate.isAbstract();
    }

    @Override
    public boolean isStatic() {
        return delegate.isStatic();
    }

    @Override
    public Optional<String> getDocumentation() {
        return delegate.getDocumentation();
    }

    @Override
    public boolean isPrivate() {
        return delegate.isPrivate();
    }

    @Override
    public boolean isFinal() {
        return delegate.isFinal();
    }

    @Override
    public boolean hasSimpleAnnotation(@Nullable String annotation) {
        return delegate.hasSimpleAnnotation(annotation);
    }

    @Override
    public boolean hasSimpleDeclaredAnnotation(@Nullable String annotation) {
        return delegate.hasSimpleDeclaredAnnotation(annotation);
    }

    @Override
    public <E extends Enum> E[] enumValues(@NonNull String annotation, Class<E> enumType) {
        return delegate.enumValues(annotation, enumType);
    }

    @Override
    public <E extends Enum> E[] enumValues(@NonNull String annotation, @NonNull String member, Class<E> enumType) {
        return delegate.enumValues(annotation, member, enumType);
    }

    @Override
    public <E extends Enum> E[] enumValues(@NonNull Class<? extends Annotation> annotation, Class<E> enumType) {
        return delegate.enumValues(annotation, enumType);
    }

    @Override
    public <E extends Enum> E[] enumValues(@NonNull Class<? extends Annotation> annotation, @NonNull String member, Class<E> enumType) {
        return delegate.enumValues(annotation, member, enumType);
    }

    @Override
    public <T> Class<T>[] classValues(@NonNull String annotation) {
        return delegate.classValues(annotation);
    }

    @Override
    public <T> Class<T>[] classValues(@NonNull String annotation, @NonNull String member) {
        return delegate.classValues(annotation, member);
    }

    @Override
    public <T> Class<T>[] classValues(@NonNull Class<? extends Annotation> annotation) {
        return delegate.classValues(annotation);
    }

    @Override
    public <T> Class<T>[] classValues(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.classValues(annotation, member);
    }

    @Override
    public <E extends Enum> Optional<E> enumValue(@NonNull String annotation, Class<E> enumType) {
        return delegate.enumValue(annotation, enumType);
    }

    @Override
    public <E extends Enum> Optional<E> enumValue(@NonNull String annotation, @NonNull String member, Class<E> enumType) {
        return delegate.enumValue(annotation, member, enumType);
    }

    @Override
    public <E extends Enum> Optional<E> enumValue(@NonNull Class<? extends Annotation> annotation, Class<E> enumType) {
        return delegate.enumValue(annotation, enumType);
    }

    @Override
    public <E extends Enum> Optional<E> enumValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member, Class<E> enumType) {
        return delegate.enumValue(annotation, member, enumType);
    }

    @Override
    public OptionalLong longValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.longValue(annotation, member);
    }

    @Override
    public Optional<Boolean> booleanValue(@NonNull String annotation, @NonNull String member) {
        return delegate.booleanValue(annotation, member);
    }

    @Override
    public Optional<Boolean> booleanValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.booleanValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<Boolean> booleanValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.booleanValue(annotation);
    }

    @Override
    @NonNull
    public Optional<Boolean> booleanValue(@NonNull String annotation) {
        return delegate.booleanValue(annotation);
    }

    @Override
    @NonNull
    public String[] stringValues(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.stringValues(annotation, member);
    }

    @Override
    @NonNull
    public String[] stringValues(@NonNull Class<? extends Annotation> annotation) {
        return delegate.stringValues(annotation);
    }

    @Override
    @NonNull
    public OptionalInt intValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.intValue(annotation, member);
    }

    @Override
    @NonNull
    public OptionalInt intValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.intValue(annotation);
    }

    @Override
    @NonNull
    public Optional<String> stringValue(@NonNull String annotation, @NonNull String member) {
        return delegate.stringValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<String> stringValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.stringValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<String> stringValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.stringValue(annotation);
    }

    @Override
    @NonNull
    public Optional<String> stringValue(@NonNull String annotation) {
        return delegate.stringValue(annotation);
    }

    @Override
    @NonNull
    public OptionalDouble doubleValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.doubleValue(annotation, member);
    }

    @Override
    @NonNull
    public OptionalDouble doubleValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.doubleValue(annotation);
    }

    @Override
    @NonNull
    public Map<String, Object> getDefaultValues(@NonNull String annotation) {
        return delegate.getDefaultValues(annotation);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull String annotation, @NonNull Argument<T> requiredType) {
        return delegate.getValue(annotation, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull Class<? extends Annotation> annotation, @NonNull Argument<T> requiredType) {
        return delegate.getValue(annotation, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull String annotation, @NonNull String member, @NonNull Argument<T> requiredType) {
        return delegate.getValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getDefaultValue(@NonNull String annotation, @NonNull String member, @NonNull Argument<T> requiredType) {
        return delegate.getDefaultValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getDefaultValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member, @NonNull Argument<T> requiredType) {
        return delegate.getDefaultValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member, @NonNull Argument<T> requiredType) {
        return delegate.getValue(annotation, member, requiredType);
    }

    @Override
    public <T extends Annotation> T synthesizeDeclared(@NonNull Class<T> annotationClass) {
        return delegate.synthesizeDeclared(annotationClass);
    }

    @Override
    @NonNull
    public <T extends Annotation> T[] synthesizeAnnotationsByType(@NonNull Class<T> annotationClass) {
        return delegate.synthesizeAnnotationsByType(annotationClass);
    }

    @Override
    @NonNull
    public <T extends Annotation> T[] synthesizeDeclaredAnnotationsByType(@NonNull Class<T> annotationClass) {
        return delegate.synthesizeDeclaredAnnotationsByType(annotationClass);
    }

    @Override
    @Nullable
    public <T extends Annotation> AnnotationValue<T> getAnnotation(@NonNull String annotation) {
        return delegate.getAnnotation(annotation);
    }

    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public <T extends Annotation> AnnotationValue<T> getAnnotation(@NonNull Class<T> annotationClass) {
        if (Introspected.class == annotationClass) {
            return (AnnotationValue<T>) AnnotationValue.builder(Introspected.class).build();
        } else {
            return delegate.getAnnotation(annotationClass);
        }
    }

    @Override
    @Nullable
    public <T extends Annotation> AnnotationValue<T> getDeclaredAnnotation(@NonNull String annotation) {
        return delegate.getDeclaredAnnotation(annotation);
    }

    @Override
    @NonNull
    public <T extends Annotation> Optional<AnnotationValue<T>> findDeclaredAnnotation(@NonNull Class<T> annotationClass) {
        return delegate.findDeclaredAnnotation(annotationClass);
    }

    @Override
    @Nullable
    public <T extends Annotation> AnnotationValue<T> getDeclaredAnnotation(@NonNull Class<T> annotationClass) {
        return delegate.getDeclaredAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(@NonNull Class<? extends Annotation> annotationClass) {
        return delegate.isAnnotationPresent(annotationClass);
    }

    @Override
    public boolean isDeclaredAnnotationPresent(@NonNull Class<? extends Annotation> annotationClass) {
        return delegate.isDeclaredAnnotationPresent(annotationClass);
    }

    @Override
    @NonNull
    public <T> Optional<T> getDefaultValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member, @NonNull Class<T> requiredType) {
        return delegate.getDefaultValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member, @NonNull Class<T> requiredType) {
        return delegate.getValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public Optional<String> getAnnotationNameByStereotype(String stereotype) {
        return delegate.getAnnotationNameByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<String> getDeclaredAnnotationNameByStereotype(String stereotype) {
        return delegate.getDeclaredAnnotationNameByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getAnnotationTypeByStereotype(@NonNull Class<? extends Annotation> stereotype) {
        return delegate.getAnnotationTypeByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getDeclaredAnnotationTypeByStereotype(@NonNull Class<? extends Annotation> stereotype) {
        return delegate.getDeclaredAnnotationTypeByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getDeclaredAnnotationTypeByStereotype(String stereotype) {
        return delegate.getDeclaredAnnotationTypeByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getAnnotationTypeByStereotype(String stereotype) {
        return delegate.getAnnotationTypeByStereotype(stereotype);
    }

    @Override
    @NonNull
    public Optional<String> getAnnotationNameByStereotype(@NonNull Class<? extends Annotation> stereotype) {
        return delegate.getAnnotationNameByStereotype(stereotype);
    }

    @Override
    @NonNull
    public <T> OptionalValues<T> getValues(@NonNull Class<? extends Annotation> annotation, @NonNull Class<T> valueType) {
        return delegate.getValues(annotation, valueType);
    }

    @Override
    @NonNull
    public List<String> getAnnotationNamesByStereotype(@NonNull Class<? extends Annotation> stereotype) {
        return delegate.getAnnotationNamesByStereotype(stereotype);
    }

    @Override
    @NonNull
    public List<Class<? extends Annotation>> getAnnotationTypesByStereotype(@NonNull Class<? extends Annotation> stereotype) {
        return delegate.getAnnotationTypesByStereotype(stereotype);
    }

    @Override
    @NonNull
    public List<Class<? extends Annotation>> getAnnotationTypesByStereotype(@NonNull Class<? extends Annotation> stereotype, @NonNull ClassLoader classLoader) {
        return delegate.getAnnotationTypesByStereotype(stereotype, classLoader);
    }

    @Override
    @NonNull
    public <T extends Annotation> Optional<AnnotationValue<T>> findAnnotation(@NonNull Class<T> annotationClass) {
        return delegate.findAnnotation(annotationClass);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull String annotation, @NonNull String member, @NonNull Class<T> requiredType) {
        return delegate.getValue(annotation, member, requiredType);
    }

    @Override
    @NonNull
    public OptionalLong longValue(@NonNull String annotation, @NonNull String member) {
        return delegate.longValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<Class> classValue(@NonNull String annotation) {
        return delegate.classValue(annotation);
    }

    @Override
    @NonNull
    public Optional<Class> classValue(@NonNull String annotation, @NonNull String member) {
        return delegate.classValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<Class> classValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.classValue(annotation);
    }

    @Override
    @NonNull
    public Optional<Class> classValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.classValue(annotation, member);
    }

    @Override
    @NonNull
    public OptionalInt intValue(@NonNull String annotation, @NonNull String member) {
        return delegate.intValue(annotation, member);
    }

    @Override
    @NonNull
    public OptionalDouble doubleValue(@NonNull String annotation, @NonNull String member) {
        return delegate.doubleValue(annotation, member);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull String annotation, @NonNull Class<T> requiredType) {
        return delegate.getValue(annotation, requiredType);
    }

    @Override
    @NonNull
    public Optional<Object> getValue(@NonNull String annotation, @NonNull String member) {
        return delegate.getValue(annotation, member);
    }

    @Override
    @NonNull
    public Optional<Object> getValue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.getValue(annotation, member);
    }

    @Override
    public boolean isTrue(@NonNull String annotation, @NonNull String member) {
        return delegate.isTrue(annotation, member);
    }

    @Override
    public boolean isTrue(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.isTrue(annotation, member);
    }

    @Override
    public boolean isPresent(@NonNull String annotation, @NonNull String member) {
        return delegate.isPresent(annotation, member);
    }

    @Override
    public boolean isPresent(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.isPresent(annotation, member);
    }

    @Override
    public boolean isFalse(@NonNull Class<? extends Annotation> annotation, @NonNull String member) {
        return delegate.isFalse(annotation, member);
    }

    @Override
    public boolean isFalse(@NonNull String annotation, @NonNull String member) {
        return delegate.isFalse(annotation, member);
    }

    @Override
    @NonNull
    public Optional<Object> getValue(@NonNull String annotation) {
        return delegate.getValue(annotation);
    }

    @Override
    @NonNull
    public Optional<Object> getValue(@NonNull Class<? extends Annotation> annotation) {
        return delegate.getValue(annotation);
    }

    @Override
    @NonNull
    public <T> Optional<T> getValue(@NonNull Class<? extends Annotation> annotation, @NonNull Class<T> requiredType) {
        return delegate.getValue(annotation, requiredType);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getAnnotationType(@NonNull String name) {
        return delegate.getAnnotationType(name);
    }

    @Override
    @NonNull
    public Optional<Class<? extends Annotation>> getAnnotationType(@NonNull String name, @NonNull ClassLoader classLoader) {
        return delegate.getAnnotationType(name, classLoader);
    }

    @Override
    public boolean hasAnnotation(@Nullable Class<? extends Annotation> annotation) {
        return delegate.hasAnnotation(annotation);
    }

    @Override
    public boolean hasStereotype(@Nullable Class<? extends Annotation> annotation) {
        if (Introspected.class == annotation) {
            return true;
        } else {
            return delegate.hasStereotype(annotation);
        }
    }

    @Override
    public boolean hasStereotype(Class<? extends Annotation>... annotations) {
        if (Arrays.stream(annotations).anyMatch(aClass -> Introspected.class == aClass)) {
            return true;
        } else {
            return delegate.hasStereotype(annotations);
        }
    }

    @Override
    public boolean hasStereotype(String[] annotations) {
        if (Arrays.stream(annotations).anyMatch(aClass -> Introspected.class.getName().equals(aClass))) {
            return true;
        } else {
            return delegate.hasStereotype(annotations);
        }
    }

    @Override
    public boolean hasDeclaredAnnotation(@Nullable Class<? extends Annotation> annotation) {
        return delegate.hasDeclaredAnnotation(annotation);
    }

    @Override
    public boolean hasDeclaredStereotype(@Nullable Class<? extends Annotation> stereotype) {
        return delegate.hasDeclaredStereotype(stereotype);
    }

    @Override
    public boolean hasDeclaredStereotype(Class<? extends Annotation>... annotations) {
        return delegate.hasDeclaredStereotype(annotations);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean hasDeclaredAnnotation(String annotation) {
        return delegate.hasDeclaredAnnotation(annotation);
    }

    @Override
    @NonNull
    public Set<String> getAnnotationNames() {
        return delegate.getAnnotationNames();
    }

    @Override
    @NonNull
    public Set<String> getDeclaredAnnotationNames() {
        return delegate.getDeclaredAnnotationNames();
    }

    @Override
    public boolean hasAnnotation(String annotation) {
        return delegate.hasAnnotation(annotation);
    }

    @Override
    public boolean hasStereotype(String annotation) {
        return delegate.hasStereotype(annotation);
    }

    @Override
    public boolean hasDeclaredStereotype(String annotation) {
        return delegate.hasDeclaredStereotype(annotation);
    }

    @Override
    @NonNull
    public List<String> getAnnotationNamesByStereotype(String stereotype) {
        return delegate.getAnnotationNamesByStereotype(stereotype);
    }

    @Override
    @NonNull
    public List<String> getDeclaredAnnotationNamesByStereotype(String stereotype) {
        return delegate.getDeclaredAnnotationNamesByStereotype(stereotype);
    }

    @Override
    @NonNull
    public <T extends Annotation> Optional<AnnotationValue<T>> findAnnotation(@NonNull String annotation) {
        return delegate.findAnnotation(annotation);
    }

    @Override
    @NonNull
    public <T> OptionalValues<T> getValues(@NonNull String annotation, @NonNull Class<T> valueType) {
        return delegate.getValues(annotation, valueType);
    }

    @Override
    @NonNull
    public <T extends Annotation> Optional<AnnotationValue<T>> findDeclaredAnnotation(@NonNull String annotation) {
        return delegate.findDeclaredAnnotation(annotation);
    }

    @Override
    @NonNull
    public <T> Optional<T> getDefaultValue(@NonNull String annotation, @NonNull String member, @NonNull Class<T> requiredType) {
        return delegate.getDefaultValue(annotation, member, requiredType);
    }

    @Override
    @Nullable
    public <T extends Annotation> T synthesize(@NonNull Class<T> annotationClass) {
        return delegate.synthesize(annotationClass);
    }

    @Override
    @NonNull
    public Annotation[] synthesizeAll() {
        return delegate.synthesizeAll();
    }

    @Override
    @NonNull
    public Annotation[] synthesizeDeclared() {
        return delegate.synthesizeDeclared();
    }

    @Override
    @NonNull
    public <T extends Annotation> List<AnnotationValue<T>> getAnnotationValuesByType(@NonNull Class<T> annotationType) {
        return delegate.getAnnotationValuesByType(annotationType);
    }

    @Override
    @NonNull
    public <T extends Annotation> List<AnnotationValue<T>> getDeclaredAnnotationValuesByType(@NonNull Class<T> annotationType) {
        return delegate.getDeclaredAnnotationValuesByType(annotationType);
    }

    @Override
    @NonNull
    public AnnotationMetadata getAnnotationMetadata() {
        return delegate.getAnnotationMetadata();
    }

    @Override
    public boolean hasDeclaredStereotype(@Nullable String... annotations) {
        return delegate.hasDeclaredStereotype(annotations);
    }

    @Override
    public boolean isDeclaredNullable() {
        return delegate.isDeclaredNullable();
    }

    @Override
    public boolean isNullable() {
        return delegate.isNullable();
    }

    @Override
    public boolean isNonNull() {
        return delegate.isNonNull();
    }

    @Override
    public boolean isDeclaredNonNull() {
        return delegate.isDeclaredNonNull();
    }
}
