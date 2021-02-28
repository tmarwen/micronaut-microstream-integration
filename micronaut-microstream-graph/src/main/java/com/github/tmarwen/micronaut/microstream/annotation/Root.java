package com.github.tmarwen.micronaut.microstream.annotation;

import io.micronaut.context.annotation.AliasFor;
import io.micronaut.core.annotation.Introspected;

import javax.inject.Singleton;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation marker for {@code MicroStream} root graph type.
 *
 * @since 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Root {

    /**
     * The list of classes that should be included for introspection.
     *
     * @return an array holding the class types to scan for introspection
     */
    Class[] types() default {};
}
