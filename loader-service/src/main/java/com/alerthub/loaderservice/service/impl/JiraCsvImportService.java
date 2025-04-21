package com.alerthub.loaderservice.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.loader.JiraCsvLoader;
import com.alerthub.loaderservice.mapper.JiraCsvToEntityMapper;
import com.alerthub.loaderservice.model.JiraCsvRecord;
import com.alerthub.loaderservice.repository.PlatformInformationRepository;
import com.alerthub.loaderservice.util.RemoteCsvDownloader;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class JiraCsvImportService {

	  @Autowired
	    private JiraCsvLoader loader;

	    @Autowired
	    private PlatformInformationRepository repository;
	    
	    @Value("${alerthub.import.jira.api}")
	    private String jiraRemoteBaseUrl;

	    public void importCsv(File file) {
	        try {
	            System.out.println("📂 Loading GitHub file: " + file.getAbsolutePath());

	            List<JiraCsvRecord> records = loader.load(file);
	            System.out.println("🟡 Jira records parsed: " + records.size());

	            List<PlatformInformation> entities = records.stream()
	                    .map(JiraCsvToEntityMapper::map)
	                    .toList();

	            System.out.println("🟢 Entities ready to insert: " + entities.size());

	            repository.saveAll(entities);

	            System.out.println("✅ Imported " + entities.size() + " Jira records.");
	        } catch (Exception e) {
	            System.err.println("❌ Failed to import Jira CSV: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    public void downloadAndImportAllCsvs() {
	        try {
	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<JsonNode> response = restTemplate.getForEntity(jiraRemoteBaseUrl, JsonNode.class);

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
	            System.err.println("❌ Failed to import ClickUp CSVs from folder: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
}
