package com.jovisco.quarkus.panache.model;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_publishers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Publisher extends PanacheEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // queries
    public static Optional<Publisher> findOneByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public static List<Publisher> listAllContainingName(String name) {
        return find("name like ?1", "%" + name + "%").list();
    }

}
