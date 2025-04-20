package com.alerthub.loaderservice.controller;

import com.alerthub.loaderservice.config.GitHubImportProperties;
import com.alerthub.loaderservice.dto.GitHubFileRequest;
import com.alerthub.loaderservice.service.impl.GitHubCsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/import/github")
public class GitHubCsvImportController {

    @Autowired
    private GitHubCsvImportService importService;
    
    @Autowired
    private GitHubImportProperties config;

    @PostMapping
    public String importGitHubData(@RequestBody GitHubFileRequest request) {
        String fullPath = config.getBasePath() + request.getFileName();
        File file = new File(fullPath);

        if (!file.exists()) {
            return "❌ File not found: " + file.getAbsolutePath();
        }

        importService.importCsv(file);
        return "✅ Import started for file: " + file.getAbsolutePath();
    }
    
    @PostMapping("/remote/all")
    public String importAllFromRemote() {
        importService.downloadAndImportAllCsvs();
        return "✅ Remote GitHub CSV import for all files started.";
    }
}
