package com.alisievich.javafxapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {
    public static AppConfig loadConfig() throws IOException {
        InputStream inputStream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("application.yml");

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(inputStream, AppConfig.class);
    }
}
