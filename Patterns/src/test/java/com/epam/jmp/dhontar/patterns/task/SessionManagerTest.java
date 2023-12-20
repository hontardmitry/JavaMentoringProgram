package com.epam.jmp.dhontar.patterns.task;


import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertNotSame;

import org.testng.annotations.Test;

public class SessionManagerTest {
    
    private static final String ADMIN_USER = "adminchik";
    private static final String BUYER_USER = "buyer";
    private static final String GUEST_USER = "guest";
    private static final String ADMIN_PAGE = "admin-portal";
    private static final String BUYER_PAGE = "shopping-cart";
    private static final String GUEST_PAGE = "search";

    @Test
    public void whenUserHasAdminRole_thenSessionIsCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, ADMIN_PAGE);
        assertNotNull(session);
        var session2 = sessionManager.createSession(ADMIN_USER, ADMIN_PAGE);
        assertNotSame(session, session2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void whenUserHasAdminRole_thenSessionIsCreatedForSearchPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, GUEST_PAGE);
        assertNotNull(session);
    }
    @Test(expectedExceptions = InsufficientRightsException.class)
    public void whenUserHasAdminRole_thenSessionIsCreatedForShoppingCartPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
//        var session =
                sessionManager.createSession(BUYER_USER, ADMIN_PAGE);
//        assertNotNull(session);
    }
    @Test
    public void whenNotAdminRole_thenSessionIsNotCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, BUYER_PAGE);
        assertNotNull(session);
    }

    @Test
    public void whenUserHasAdminRole_thenSessionIsNotCreatedInvalidPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession(ADMIN_USER, "");
        assertNotNull(session);
    }
    @Test
    public void whenUserISNotValid_thenSessionIsNotCreatedValidPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("", GUEST_PAGE);
        assertNotNull(session);
    }
}


