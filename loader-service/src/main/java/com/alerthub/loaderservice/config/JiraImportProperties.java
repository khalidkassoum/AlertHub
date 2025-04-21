package com.alerthub.loaderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JiraImportProperties {
	 @Value("${alerthub.import.jira.path}")
	    private String basePath;

	    public String getBasePath() {
	        return basePath;
	    }
}
