package com.alerthub.loaderservice.mapper;

import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.model.ClickUpCsvRecord;

import java.time.LocalDateTime;

public class ClickUpCsvToEntityMapper {

    public static PlatformInformation map(ClickUpCsvRecord record) {
        return PlatformInformation.builder()
                .timestamp(LocalDateTime.now())
                .ownerId(parseIntSafe(record.getOwnerId()))
                .project(record.getProject())
                .tag(record.getTag())
                .label(record.getLabel())
                .developerId(parseIntSafe(record.getWorkerId()))
                .taskNumber((record.getTask()))
                .environment(record.getEnvironment())
                .userStory(record.getUserStory())
                .taskPoint(parseIntSafe(record.getPoint()))
                .sprint(record.getSprint())
                .platform("ClickUp")
                .build();
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            System.out.println("⚠️ Warning: failed to parse int from value: " + value);
            return 0;
        }
    }
}
