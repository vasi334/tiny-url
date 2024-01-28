package com.tiny_url.shortenerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "url_map")
public class UrlEntity {
    @Id
    private String hash;
    private String originalUrl;
    private LocalDateTime expireDate;
    private String username;
}
