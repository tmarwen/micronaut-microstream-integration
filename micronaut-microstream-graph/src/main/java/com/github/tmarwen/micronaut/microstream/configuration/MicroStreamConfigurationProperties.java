package com.github.tmarwen.micronaut.microstream.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * MicroStreamConfiguration
 *
 * @since 1.0.0
 */
@ConfigurationProperties(MicroStreamConfigurationProperties.PREFIX)
public class MicroStreamConfigurationProperties {

    public static final String PREFIX = "microstream";
}
