package com.jovisco.quarkus.panache.model;

import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;

import java.time.Duration;
import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "t_tracks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Track extends PanacheEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    public Duration duration;

    @JoinColumn(name = "cd_fk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    public CD cd;

    @Column(name = "created_at", nullable = false)
    @Builder.Default
    public Instant createdAt = Instant.now();

}