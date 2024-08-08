package net.andrecarbajal.urlshortener.domain.url;

import jakarta.validation.constraints.NotBlank;

public record UrlRecord(@NotBlank String originalUrl) {
}
