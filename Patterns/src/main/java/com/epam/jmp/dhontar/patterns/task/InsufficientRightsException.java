package com.epam.jmp.dhontar.patterns.task;

import static java.lang.String.format;

public class InsufficientRightsException extends Exception {
    public InsufficientRightsException(String user, String accessedPath) {
        super(format("User %s has no access to %s", user, accessedPath));
    }
}
