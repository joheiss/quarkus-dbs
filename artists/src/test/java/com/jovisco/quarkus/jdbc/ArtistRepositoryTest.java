package com.jovisco.quarkus.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  @Test
  public void shouldCreateAndFindAnArtist() throws SQLException {
    Artist artist = Artist.builder()
        .name("name")
        .bio("bio")
        .build();

    repository.persist(artist);
    assertNotNull(artist.getId());

    var found = repository.findById(artist.getId());
    assertEquals("name", found.getName());
  }
}