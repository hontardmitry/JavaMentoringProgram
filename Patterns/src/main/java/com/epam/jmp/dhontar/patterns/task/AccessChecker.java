package com.epam.jmp.dhontar.patterns.task;

public class AccessChecker {


    private static final String CONFIG_FILE_PATH = "...";



    public String getAccessLevel(User u) {
        // This is a placeholder. You'll need to implement your own logic to get a user's access level.
        return "user";
    }

    public boolean mayAccess(User user, String accessedPath) {
        return false;
    }
}

