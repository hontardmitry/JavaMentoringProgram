package com.epam.jmp.dhontar.patterns.task;

public class SessionManager {
    private final AccessChecker access = AccessChecker.getInstance();

    public Session createSession(String user, String accessedPath) throws InsufficientRightsException {
        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}
