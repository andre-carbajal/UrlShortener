package net.andrecarbajal.urlshortener.infra;

public class UrlException {
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static class AuthException extends RuntimeException {
        public AuthException(String message) {
            super(message);
        }
    }
}
