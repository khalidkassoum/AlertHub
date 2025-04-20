package com.alerthub.loaderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

@Entity
@Table(name = "platformInformation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @JsonProperty("owner_id")
    @Column(name = "owner_id")
    private int ownerId;

    @JsonProperty("project")
    private String project;
    
    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("label")
    private String label;

    @CsvBindByName(column = "worker_id")
    @JsonProperty("worker_id")
    @Column(name = "developer_id") 
    private int developerId;

    @CsvBindByName(column = "task")
    @JsonProperty("task")
    @Column(name = "task_number")
    private String taskNumber;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("user_story")
    @Column(name = "user_story", columnDefinition = "TEXT")
    private String userStory;

    @JsonProperty("task_point")
    @Column(name = "task_point")
    private int taskPoint;

    @JsonProperty("sprint")
    private String sprint;
    
    @JsonProperty("platform")
    private String platform;
}
