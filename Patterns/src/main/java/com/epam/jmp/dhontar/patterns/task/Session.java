package com.epam.jmp.dhontar.patterns.task;

public class Session {
    private final String user;
    private final long createdTime = System.currentTimeMillis();
    public Session(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public boolean isSessionValid() {
        return (System.currentTimeMillis() - createdTime) < 30 * 1000 * 60;
    }
}
