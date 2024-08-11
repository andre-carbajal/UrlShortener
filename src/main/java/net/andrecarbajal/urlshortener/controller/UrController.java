package net.andrecarbajal.urlshortener.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.andrecarbajal.urlshortener.domain.url.Url;
import net.andrecarbajal.urlshortener.domain.url.UrlRecord;
import net.andrecarbajal.urlshortener.domain.url.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class UrController {

    private final UrlService urlService;

    @GetMapping("/api/urls")
    public ResponseEntity<List<Url>> getAllUrls() {
        return ResponseEntity.ok(urlService.getAllUrls());
    }

    @PutMapping("/api/urls/{id}")
    @Transactional
    public ResponseEntity<Void> updateUrl(@RequestHeader("Authorization") String auth, @PathVariable Long id, @RequestBody UrlRecord data) {
        return urlService.updateUrlCode(auth, id, data);
    }

    @DeleteMapping("/api/urls/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUrl(@RequestHeader("Authorization") String auth, @PathVariable Long id) {
        return urlService.deleteUrl(auth, id);
    }
}
