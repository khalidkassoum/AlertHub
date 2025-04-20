package com.alerthub.loaderservice.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alerthub.loaderservice.config.JiraImportProperties;
import com.alerthub.loaderservice.dto.JiraFileRequest;
import com.alerthub.loaderservice.service.impl.JiraCsvImportService;

@RestController
@RequestMapping("/api/import/jira")
public class JiraCsvImportController {
	
	 @Autowired
	 private JiraCsvImportService importService;
	    
	 @Autowired
	 private JiraImportProperties config;

	 @PostMapping
	 public String importJiraData(@RequestBody JiraFileRequest request) {
	        String fullPath = config.getBasePath() + request.getFileName();
	        File file = new File(fullPath);

	        if (!file.exists()) {
	            return "❌ File not found: " + file.getAbsolutePath();
	        }

	        importService.importCsv(file);
	        return "✅ Import started for file: " + file.getAbsolutePath();
	    }
	 
	    @PostMapping("/remote/all")
	    public String importRemoteClickUpData() {
	        importService.downloadAndImportAllCsvs();
	        return "✅ Remote ClickUp CSV import started.";
	    }
}
