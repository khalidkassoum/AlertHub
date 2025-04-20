package com.alerthub.loaderservice.loader;

import com.alerthub.loaderservice.model.GitHubCsvRecord;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Component
public class GitHubCsvLoader {

    public List<GitHubCsvRecord> load(File file) {
        try (FileReader reader = new FileReader(file)) {
            CsvToBean<GitHubCsvRecord> csvToBean = new CsvToBeanBuilder<GitHubCsvRecord>(reader)
                    .withType(GitHubCsvRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error loading GitHub CSV file", e);
        }
    }
}
