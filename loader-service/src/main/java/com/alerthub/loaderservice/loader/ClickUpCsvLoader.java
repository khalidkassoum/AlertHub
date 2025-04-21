package com.alerthub.loaderservice.loader;

import com.alerthub.loaderservice.model.ClickUpCsvRecord;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Component
public class ClickUpCsvLoader {

    public List<ClickUpCsvRecord> load(File file) {
        try (FileReader reader = new FileReader(file)) {
            CsvToBean<ClickUpCsvRecord> csvToBean = new CsvToBeanBuilder<ClickUpCsvRecord>(reader)
                    .withType(ClickUpCsvRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error loading ClickUp CSV file", e);
        }
    }
}
