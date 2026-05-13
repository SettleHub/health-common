package org.settlehub.commons.health.config;

import org.settlehub.commons.health.HealthCheckController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfiguration {

    @Bean
    public HealthCheckController healthCheckController() {
        return new HealthCheckController();
    }
    
}
