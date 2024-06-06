package com.ila.hr.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("db.file")
public class CustomDbConfig {
    public String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}