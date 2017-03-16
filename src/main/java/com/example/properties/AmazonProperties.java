package com.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2017-03-16.
 */

@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {
    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getAssociateId() {
        return associateId;
    }
}
