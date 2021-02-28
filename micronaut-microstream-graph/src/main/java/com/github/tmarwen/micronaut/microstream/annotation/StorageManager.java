package com.github.tmarwen.micronaut.microstream.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Configuration annotation for {@code MicroStream} storage manager allowing to
 * inject custom configuration upon compile-time.
 *
 * @since 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface StorageManager {

    /**
     * Root type member key.
     */
    String ROOT_MEMBER = "root";

    /**
     * The class type of the root entity.
     *
     * @return the type of the root graph entity
     */
    Class<?> root();
}
