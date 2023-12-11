package com.epam.jmp.dhontar.lynda.behavioral.chain;

public class BasicAuthenticationHandler extends AuthenticationHandler {


    public BasicAuthenticationHandler(AuthenticationHandler next) {
        super(next);
    }

    public void handleRequest(String requestType){
        if ("basic".equals(requestType)){
            System.out.println("Basic authentication is used");
        } else {
            super.handleRequest(requestType);
        }
    }
}
