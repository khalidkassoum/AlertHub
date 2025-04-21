package com.alerthub.loaderservice.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class RemoteCsvDownloader {
    public static File downloadCsv(String url) throws IOException {
        URL fileUrl = new URL(url);
        File tempFile = File.createTempFile("remote_csv_", ".csv");
        try (InputStream in = fileUrl.openStream()) {
            Files.copy(in, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile;
    }
}