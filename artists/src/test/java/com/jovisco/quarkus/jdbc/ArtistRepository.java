package com.jovisco.quarkus.jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

import jakarta.inject.Inject;
import javax.sql.DataSource;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  DataSource dataSource;

  public void persist(Artist artist) throws SQLException {
    var conn = dataSource.getConnection();
    var sql = "INSERT INTO t_artists (id, name, bio, created_at) VALUES (?, ?, ?, ?)";
    artist.setId(Math.abs(new Random().nextLong()));

    try (var ps = conn.prepareStatement(sql)) {
      ps.setLong(1, artist.getId());
      ps.setString(2, artist.getName());
      ps.setString(3, artist.getBio());
      ps.setTimestamp(4, Timestamp.from(artist.getCreatedAt()));
      ps.executeUpdate();
    }
    conn.close();
  }

  public Artist findById(Long id) throws SQLException {
    var conn = dataSource.getConnection();
    var sql = "SELECT id, name, bio, created_at FROM t_artists WHERE id = ?";
    var artist = Artist.builder().build();

    try (var ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);
      var rs = ps.executeQuery();
      if (rs.next()) {
        artist.setId(rs.getLong(1));
        artist.setName(rs.getString(2));
        artist.setBio(rs.getString(3));
        artist.setCreatedAt(rs.getTimestamp(4).toInstant());
      }
    }
    conn.close();
    return artist;
  }
}
