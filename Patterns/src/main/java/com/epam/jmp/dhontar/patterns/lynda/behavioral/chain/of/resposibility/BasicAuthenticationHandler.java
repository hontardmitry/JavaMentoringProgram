package com.epam.jmp.dhontar.patterns.lynda.behavioral.chain.of.resposibility;

public class BasicAuthenticationHandler extends AuthenticationHandler {


    public BasicAuthenticationHandler(AuthenticationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(String requestType){
        if ("basic".equals(requestType)){
            System.out.println("Basic authentication is used");
        } else {
            super.handleRequest(requestType);
        }
    }
}
