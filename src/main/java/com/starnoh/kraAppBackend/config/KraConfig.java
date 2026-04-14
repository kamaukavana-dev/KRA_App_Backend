package com.starnoh.kraAppBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KraConfig {

    @Value("${kra.consumer.key}")
    private String consumerKey;

    @Value("${kra.consumer.secret}")
    private String consumerSecret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }


}
