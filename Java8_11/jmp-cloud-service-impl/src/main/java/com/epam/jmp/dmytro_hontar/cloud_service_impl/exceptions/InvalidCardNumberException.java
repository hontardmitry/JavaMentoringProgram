package com.epam.jmp.dmytro_hontar.cloud_service_impl.exceptions;

public class InvalidCardNumberException extends IllegalArgumentException{
    public InvalidCardNumberException() {
        super("Invalid card number is used");
    }
}
