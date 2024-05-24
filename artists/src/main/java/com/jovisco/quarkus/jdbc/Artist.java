package com.jovisco.quarkus.jdbc;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Artist {
    private Long id;
    private String name;
    private String bio;
    @Builder.Default
    private Instant createdAt = Instant.now();
}
