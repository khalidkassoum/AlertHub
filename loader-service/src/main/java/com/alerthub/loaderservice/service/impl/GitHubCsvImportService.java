package com.alerthub.loaderservice.service.impl;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.loader.GitHubCsvLoader;
import com.alerthub.loaderservice.mapper.GitHubCsvToEntityMapper;
import com.alerthub.loaderservice.model.GitHubCsvRecord;
import com.alerthub.loaderservice.repository.PlatformInformationRepository;
import com.alerthub.loaderservice.util.RemoteCsvDownloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GitHubCsvImportService {

    @Autowired
    private GitHubCsvLoader loader;

    @Autowired
    private PlatformInformationRepository repository;
    
    @Value("${alerthub.import.github.api}")
    private String githubRemoteBaseUrl;
    
    public void importCsv(File file) {
        try {
            System.out.println("📂 Loading GitHub file: " + file.getAbsolutePath());

            List<GitHubCsvRecord> records = loader.load(file);
            System.out.println("🟡 GitHub records parsed: " + records.size());

            List<PlatformInformation> entities = records.stream()
                    .map(GitHubCsvToEntityMapper::map)
                    .toList();

            System.out.println("🟢 Entities ready to insert: " + entities.size());

            repository.saveAll(entities);

            System.out.println("✅ Imported " + entities.size() + " GitHub records.");
        } catch (Exception e) {
            System.err.println("❌ Failed to import GitHub CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void downloadAndImportAllCsvs() {
        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(githubRemoteBaseUrl, JsonNode.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode files = response.getBody();
                for (JsonNode file : files) {
                    String fileName = file.get("name").asText();
                    if (fileName.endsWith(".csv")) {
                    	String downloadUrl = file.get("download_url").asText();
                        File tempFile = RemoteCsvDownloader.downloadCsv(downloadUrl);
                        importCsv(tempFile);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Failed to import GitHub CSVs from folder: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
