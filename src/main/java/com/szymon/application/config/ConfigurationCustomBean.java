package com.szymon.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationCustomBean {

    @Bean("beanName")
    String getName() {
        return "String bean";
    }
}
