package com.epam.jmp.dhontar.lynda.behavioral.chain.of.resposibility;

public class DigestAuthenticationHandler extends AuthenticationHandler {


    public DigestAuthenticationHandler(AuthenticationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(String requestType){
        if ("digest".equals(requestType)){
            System.out.println("Digest authentication is used");
        } else {
        super.handleRequest(requestType);
    }
    }
}
