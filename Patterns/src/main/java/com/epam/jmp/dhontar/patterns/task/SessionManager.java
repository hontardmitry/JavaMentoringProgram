package com.epam.jmp.dhontar.patterns.task;

/**
 * The type Session manager.
 */
public class SessionManager {

    private final AccessChecker access = AccessChecker.getInstance();

    /**
     * Creates session if user may access to the path.
     *
     * @param user         the user
     * @param accessedPath the accessed path
     * @return the session
     * @throws InsufficientRightsException the insufficient rights exception
     */
    public Session createSession(String user, String accessedPath) throws InsufficientRightsException {
        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, accessedPath);
        }
    }
}
