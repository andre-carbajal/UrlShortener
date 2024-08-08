package net.andrecarbajal.urlshortener.domain.url;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Url")
@Table(name = "url")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "original_url")
    private String originalUrl;

    @JoinColumn(name = "url_code")
    private String urlCode;
}
