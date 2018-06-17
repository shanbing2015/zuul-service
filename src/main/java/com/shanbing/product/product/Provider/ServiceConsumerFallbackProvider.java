package com.shanbing.product.product.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceConsumerFallbackProvider implements ZuulFallbackProvider{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init(){
        logger.info("ServiceConsumerFallbackProvider init");
    }

    @Override
    public String getRoute() {
        return "mail-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new FallbackClientHttpResponse();
    }
}
