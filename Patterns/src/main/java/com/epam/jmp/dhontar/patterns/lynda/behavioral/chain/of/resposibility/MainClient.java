package com.epam.jmp.dhontar.patterns.lynda.behavioral.chain.of.resposibility;

public class MainClient {

    public static void main(String[] args) {
        AuthenticationHandler chain = new BasicAuthenticationHandler(new ClientCertificateAuthenticationHandler(
                        new DigestAuthenticationHandler(null))
        );

        chain.handleRequest("client");
        chain.handleRequest("basic");
        chain.handleRequest("digest");
    }
}
