package net.andrecarbajal.urlshortener.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(UrlException.ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidUrlException(UrlException.ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UrlException.AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleAuthException(UrlException.AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
