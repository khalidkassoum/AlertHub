package com.alerthub.loaderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GitHubImportProperties {

    @Value("${alerthub.import.github.path}")
    private String basePath;

    public String getBasePath() {
        return basePath;
    }
}
