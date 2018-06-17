package com.shanbing.product.product.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FilterConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init(){
        logger.info("FilterConfiguration init");
    }

    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
