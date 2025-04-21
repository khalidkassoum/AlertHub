package com.alerthub.loaderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClickUpImportProperties {

    @Value("${alerthub.import.clickup.path}")
    private String basePath;

    public String getBasePath() {
        return basePath;
    }
}
