package com.epam.jmp.dhontar.patterns.task;


import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class SessionManagerTest {

    @Test
    public void whenUserHasAdminRole_thenSessionIsCreatedForAdminPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("adminchik", "admin-portal");
        assertNotNull(session);
    }

    @Test
    public void whenUserHasAdminRole_thenSessionIsCreatedForSearchPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("adminchik", "search");
        assertNotNull(session);
    }
    @Test
    public void whenUserHasAdminRole_thenSessionIsCreatedForShoppingCartPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("adminchik", "shopping-cart");
        assertNotNull(session);
    }

    @Test
    public void whenUserHasAdminRole_thenSessionIsNotCreatedInvalidPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("adminchik", "test");
        assertNotNull(session);
    }
    @Test
    public void whenUserISNotValid_thenSessionIsNotCreatedValidPage() throws InsufficientRightsException {
        var sessionManager = new SessionManager();
        var session = sessionManager.createSession("", "search");
        assertNotNull(session);
    }
}


