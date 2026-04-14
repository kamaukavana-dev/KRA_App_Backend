package com.starnoh.kraAppBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kra.consumer")
public class KraConfig {

    private String consumerKey;
    private String consumerSecret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }


    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }




}
