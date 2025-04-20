package com.alerthub.loaderservice.loader;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alerthub.loaderservice.model.JiraCsvRecord;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class JiraCsvLoader {

    public List<JiraCsvRecord> load(File file) {
        try (FileReader reader = new FileReader(file)) {
            CsvToBean<JiraCsvRecord> csvToBean = new CsvToBeanBuilder<JiraCsvRecord>(reader)
                    .withType(JiraCsvRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error loading Jira CSV file", e);
        }
    }
}
