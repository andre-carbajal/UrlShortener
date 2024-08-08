package net.andrecarbajal.urlshortener.domain.url;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findByOriginalUrl(String originalUrl);

    Optional<Url> findByUrlCode(String shortUrl);
}
