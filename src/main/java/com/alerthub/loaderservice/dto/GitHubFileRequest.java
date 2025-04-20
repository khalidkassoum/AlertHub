package com.alerthub.loaderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubFileRequest {
    @JsonProperty("fileName")
    private String fileName;
}
