package com.epam.jmp.dhontar.patterns.task;

/**
 * The type Session.
 */
public class Session {

    private final String user;
    private final long createdTime = System.currentTimeMillis();

    /**
     * Instantiates a new Session.
     *
     * @param user the user
     */
    public Session(String user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns boolean Is session valid.
     *
     * @return the boolean
     */
    public boolean isSessionValid() {
        return (System.currentTimeMillis() - createdTime) < 30 * 1000 * 60;
    }
}
