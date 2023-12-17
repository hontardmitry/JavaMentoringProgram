package com.epam.jmp.dhontar.patterns.task;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccessChecker {

private static final String ADMIN = "admin";
private static final String SEARCHER = "searcher";
private static final String BUYER = "buyer";
    private static AccessChecker instance;
    private final ServerConfig config = ServerConfig.getInstance();
    private static final Map<String, List<String>> pagesAccess = Map.of(
            "admin-portal", Collections.singletonList(ADMIN),
            "search",  List.of(ADMIN, SEARCHER, BUYER),
            "shopping-cart", List.of(ADMIN, BUYER),
            "checkout",  List.of(ADMIN, BUYER)
    );


    private AccessChecker() {
    }

    public static synchronized AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    public boolean mayAccess(String user, String accessedPath) {
        List<String> accessPathEntry = Optional.ofNullable(pagesAccess.get(accessedPath))
                .orElseThrow(IllegalArgumentException::new);
        return Optional.of(accessPathEntry
                .contains(config.getAccessLevel(user))) //Null pointer is not caught here
                .orElseThrow(IllegalArgumentException::new);
    }

}

