package com.szymon.application.factory;

import com.szymon.application.config.BeanExampleFirst;
import com.szymon.application.config.BeanExampleSecond;
import com.szymon.application.config.IBeanExample;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanExampleFactory {

    @Bean
    IBeanExample createBeanExample() {
        if(true) {
            return new BeanExampleFirst();
        }
        return new BeanExampleSecond();
    }
}
