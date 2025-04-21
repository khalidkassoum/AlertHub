package com.alerthub.loaderservice.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ClickUpCsvRecord {

    @CsvBindByName(column = "timestamp")
    private String timestamp;
    
    @CsvBindByName(column = "owner_id")
    private String ownerId;

    @CsvBindByName(column = "project")
    private String project;
    
    @CsvBindByName(column = "tag")
    private String tag;

    @CsvBindByName(column = "label")
    private String label;

    @CsvBindByName(column = "worker_id") // developer_id
    private String workerId;

    @CsvBindByName(column = "task")
    private String task;

    @CsvBindByName(column = "pr_env")
    private String environment;

    @CsvBindByName(column = "user_story")
    private String userStory;

    @CsvBindByName(column = "day")//point
    private String point;

    @CsvBindByName(column = "currant_sprint")
    private String sprint;
    
    @CsvBindByName(column = "platform")
    private String platform;
}
