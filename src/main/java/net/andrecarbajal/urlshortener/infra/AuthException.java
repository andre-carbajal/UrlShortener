package net.andrecarbajal.urlshortener.infra;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
