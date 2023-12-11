package com.epam.jmp.dhontar.lynda.behavioral.chain.of.resposibility;

public class ClientCertificateAuthenticationHandler extends AuthenticationHandler {


    public ClientCertificateAuthenticationHandler(AuthenticationHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(String requestType){
        if ("client".equals(requestType)){
            System.out.println("Client certificate authentication is used");
        } else {
            super.handleRequest(requestType);
        }
    }
}
