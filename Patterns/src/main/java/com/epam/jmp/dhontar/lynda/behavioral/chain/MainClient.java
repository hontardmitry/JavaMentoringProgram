package com.epam.jmp.dhontar.lynda.behavioral.chain;

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
