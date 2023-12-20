package com.epam.jmp.dhontar.patterns.task;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


public class ServerConfig {

    private static final String CONFIG_FILE_PATH = "C:\\EPAM\\JavaMentoringProgram\\Patterns\\src\\main\\resources\\config.json";
    private static final File configFile = new File(CONFIG_FILE_PATH);
    private static ServerConfig instance;
    private final Config config;

    private ServerConfig() {
        var objectMapper = new ObjectMapper();
        try {
            config = objectMapper.readValue(configFile, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    public String getAccessLevel(String user) {
        return Optional.ofNullable(config.getUsers().get(user))
                .orElseThrow(IllegalArgumentException::new);
    }
}
