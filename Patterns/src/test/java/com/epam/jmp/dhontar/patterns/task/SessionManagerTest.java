package com.epam.jmp.dhontar.patterns.task;


import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

public class SessionManagerTest {
    
    private static final String ADMIN_USER = "adminchik";
    private static final String BUYER_USER = "buyer";
    private static final String ADMIN_PAGE = "admin-portal";
    private static final String BUYER_PAGE = "shopping-cart";
    private static final String GUEST_PAGE = "search";

    @Test
    public void whenUserHasAdminRole_thenSessionIsCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, ADMIN_PAGE);
        assertNotNull(session);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenUserHasAdminRole_thenSessionIsCreatedForSearchPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, GUEST_PAGE);
        assertNotNull(session);
    }
    @Test(expectedExceptions = InsufficientRightsException.class)
    public void whenUserHasNoAdminRole_thenSessionIsNotCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(BUYER_USER, ADMIN_PAGE);
        assertNull(session);
    }
    @Test
    public void whenNotAdminRole_thenSessionIsNotCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, BUYER_PAGE);
        assertNotNull(session);
    }

    @Test(expectedExceptions = InsufficientRightsException.class)
    public void whenUserHasAdminRole_thenSessionIsNotCreatedInvalidPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        sessionManager.createSession(ADMIN_USER, "");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenUserISNotValid_thenSessionIsNotCreatedValidPage() throws IllegalArgumentException, InsufficientRightsException {
        var sessionManager = new SessionManager();
        sessionManager.createSession("", GUEST_PAGE);
    }
}


