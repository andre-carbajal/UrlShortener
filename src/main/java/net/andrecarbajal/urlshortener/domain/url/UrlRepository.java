package net.andrecarbajal.urlshortener.domain.url;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
