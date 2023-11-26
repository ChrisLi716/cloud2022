package com.chris.cloud;

import com.chris.cloud.service.ISmsPlugin;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EnablePluginRegistries({ISmsPlugin.class})
public class Cloud2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Cloud2022Application.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> config(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("fuck", applicationName);
    }

}
