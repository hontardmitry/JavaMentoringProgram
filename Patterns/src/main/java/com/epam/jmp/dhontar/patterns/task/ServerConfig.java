package com.epam.jmp.dhontar.patterns.task;

public class ServerConfig {

    private static ServerConfig instance;
    private void ServerConfig() {
        // load configuration from file
        // validate
    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }
}
