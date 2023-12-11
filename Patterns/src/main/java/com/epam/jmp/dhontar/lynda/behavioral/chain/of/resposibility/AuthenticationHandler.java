package com.epam.jmp.dhontar.lynda.behavioral.chain.of.resposibility;

public abstract class AuthenticationHandler {

    private final AuthenticationHandler next;
    public AuthenticationHandler(AuthenticationHandler next) {
        this.next = next;
    }

    public void handleRequest(String requestType) {
        if (next != null) {
            next.handleRequest(requestType);
        }
    }
}
