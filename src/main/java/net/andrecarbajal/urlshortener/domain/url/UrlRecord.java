package net.andrecarbajal.urlshortener.domain.url;

import jakarta.validation.constraints.NotNull;

public record UrlRecord(@NotNull String originalUrl, @NotNull String urlCode) {
}
