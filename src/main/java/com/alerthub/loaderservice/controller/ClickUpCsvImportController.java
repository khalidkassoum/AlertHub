package com.alerthub.loaderservice.controller;

import com.alerthub.loaderservice.config.ClickUpImportProperties;
import com.alerthub.loaderservice.dto.ClickUpFileRequest;
import com.alerthub.loaderservice.service.impl.ClickUpCsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/import/clickup")
public class ClickUpCsvImportController {

    @Autowired
    private ClickUpCsvImportService importService;
    
    @Autowired
    private ClickUpImportProperties config;

    @PostMapping
    public String importClickUpData(@RequestBody ClickUpFileRequest request) {
    	String fullPath = config.getBasePath() + request.getFileName();
        File file = new File(fullPath);

        if (!file.exists()) {
            return "❌ File not found: " +  file.getAbsolutePath();
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
