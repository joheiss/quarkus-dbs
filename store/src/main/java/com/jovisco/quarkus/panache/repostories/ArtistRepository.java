package com.jovisco.quarkus.panache.repostories;

import com.jovisco.quarkus.jdbc.Artist;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {}
