package com.alerthub.loaderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClickUpFileRequest {
	 @JsonProperty("fileName")
    private String fileName;
}
