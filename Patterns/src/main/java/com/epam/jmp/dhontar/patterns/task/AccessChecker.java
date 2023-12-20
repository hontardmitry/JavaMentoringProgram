package com.epam.jmp.dhontar.patterns.task;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Access checker.
 */
public class AccessChecker {

    private static final String ADMIN = "admin";
    private static final String SEARCHER = "searcher";
    private static final String BUYER = "buyer";
    private static AccessChecker instance;
    private final ServerConfig config = ServerConfig.getInstance();
    private static final Map<String, List<String>> pagesAccess = Map.of(
            "admin-portal", Collections.singletonList(ADMIN),
            "search", List.of(ADMIN, SEARCHER, BUYER),
            "shopping-cart", List.of(ADMIN, BUYER),
            "checkout", List.of(ADMIN, BUYER)
    );


    private AccessChecker() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    /**
     * Returns boolean showing if user may access to path.
     *
     * @param user         the user
     * @param accessedPath the accessed path
     * @return the boolean
     */
    public boolean mayAccess(String user, String accessedPath) {
        return
                Optional.ofNullable(pagesAccess.get(accessedPath))
                        .map(access -> access.contains(config.getAccessLevel(user)))
                        .orElse(Boolean.FALSE);
    }

}

