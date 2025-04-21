package com.alerthub.loaderservice.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class JiraCsvRecord {

	@CsvBindByName(column = "manager_id")
    private String managerId;
	
    @CsvBindByName(column = "projects")
    private String projects;

    @CsvBindByName(column = "assignee")
    private String assignee;
    
     @CsvBindByName(column = "label")
    private String label;
    
    @CsvBindByName(column = "employeeID")
    private String developerId;

    @CsvBindByName(column = "issue")
    private String taskNumber;

    @CsvBindByName(column = "env")
    private String environment;

    @CsvBindByName(column = "user_story")
    private String userStory;
    
    @CsvBindByName(column = "point")
    private String point;
    
    @CsvBindByName(column = "sprint")
    private String sprint;
    
    @CsvBindByName(column = "platform")
    private String platform;
}
