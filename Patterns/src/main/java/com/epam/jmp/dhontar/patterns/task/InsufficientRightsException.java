package com.epam.jmp.dhontar.patterns.task;

import static java.lang.String.format;

/**
 * The type Insufficient rights exception.
 */
public class InsufficientRightsException extends Exception {

    /**
     * Instantiates a new Insufficient rights exception.
     *
     * @param user         the user
     * @param accessedPath the accessed path
     */
    public InsufficientRightsException(String user, String accessedPath) {
        super(format("User %s has no access to %s", user, accessedPath));
    }
}
