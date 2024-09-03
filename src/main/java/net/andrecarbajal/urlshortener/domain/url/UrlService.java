package net.andrecarbajal.urlshortener.domain.url;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.urlshortener.infra.UrlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    @Getter
    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.auth}")
    private String authCode;

    public String shortenUrl(String originalUrl, String codeInput, String authInput) {
        if (!authCode.equals(authInput)) {
            throw new UrlException.AuthException("Invalid auth code");
        }

        if (isNotValidUrl(originalUrl)) {
            throw new UrlException.ValidationException("Invalid URL format");
        }

        List<Url> existingUrls = urlRepository.findByOriginalUrl(originalUrl);
        if (!existingUrls.isEmpty()) {
            return baseUrl + existingUrls.getFirst().getUrlCode();
        }

        if (codeInput.isEmpty()) {
            codeInput = generateCode();
        }

        Url url = Url.builder().originalUrl(originalUrl).urlCode(codeInput).build();
        urlRepository.save(url);

        return baseUrl + codeInput;
    }

    public String getOriginalUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByUrlCode(shortUrl);
        if (url.isPresent()) {
            Url foundUrl = url.get();
            foundUrl.incrementVisits();
            urlRepository.save(foundUrl);
            return foundUrl.getOriginalUrl();
        }
        return null;
    }

    public Url getUrlStats(String urlCode) {
        return urlRepository.findByUrlCode(urlCode).orElse(null);
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll().reversed();
    }

    public ResponseEntity<Void> deleteUrl(String authInput, String urlCode) {
        if (!authCode.equals(authInput)) {
            throw new UrlException.AuthException("Invalid auth code");
        }

        Url url = urlRepository.findByUrlCode(urlCode).orElse(null);

        if (url == null) {
            return ResponseEntity.notFound().build();
        }

        urlRepository.delete(url);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> updateUrlCode(String authInput, String url_code, UrlRecord data) {
        if (!authCode.equals(authInput)) {
            throw new UrlException.AuthException("Invalid auth code");
        }

        Url url = urlRepository.findByUrlCode(url_code).orElse(null);

        if (url == null) {
            return ResponseEntity.notFound().build();
        }

        url.setOriginalUrl(data.originalUr());

        url.setUpdatedAt(LocalDateTime.now());

        urlRepository.save(url);
        return ResponseEntity.ok().build();
    }

    private boolean isNotValidUrl(String url) {
        String URL_REGEX = "^(https?|ftp)://([a-zA-Z0-9.-]+)(:[0-9]+)?(/.*)?$";
        Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

        if (!URL_PATTERN.matcher(url).matches()) {
            return true;
        }

        try {
            URL urlObj = new URI(url).toURL();
            String protocol = urlObj.getProtocol();
            String host = urlObj.getHost();

            if (protocol == null || host == null || host.isEmpty()) {
                return true;
            }

            String[] hostParts = host.split("\\.");
            return hostParts.length < 2 || hostParts[0].isEmpty() || hostParts[hostParts.length - 1].isEmpty();
        } catch (MalformedURLException | URISyntaxException e) {
            return true;
        }
    }

    private String generateCode() {
        String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int CODE_LENGTH = 6;
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHAR_SET.charAt(new SecureRandom().nextInt(CHAR_SET.length())));
        }
        return code.toString();
    }
}