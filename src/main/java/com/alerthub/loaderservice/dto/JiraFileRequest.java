package com.alerthub.loaderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JiraFileRequest {
    @JsonProperty("fileName")
    private String fileName;
}
