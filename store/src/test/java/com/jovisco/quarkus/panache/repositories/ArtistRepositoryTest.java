package com.jovisco.quarkus.panache.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.jovisco.quarkus.jdbc.Artist;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ArtistRepositoryTest {

  @Inject
  ArtistRepository repository;

  @Test
  @TestTransaction
  public void shouldCreateAndFindAnArtist() throws SQLException {

    Artist artist = Artist.builder()
        .name("name")
        .bio("bio")
        .build();

    repository.persist(artist);
    assertNotNull(artist.getId());

    var found = repository.findById(artist.getId());
    assertEquals("name", found.getName());

    // count artists on database
    var count = repository.count();
    assertTrue(count > 0);
    assertEquals(repository.listAll().size(), count);
    assertEquals(repository.listAllSorted().size(), count);

    // delete artist
    repository.deleteById(artist.getId());
    assertEquals(repository.count(), count - 1);
  }
}
