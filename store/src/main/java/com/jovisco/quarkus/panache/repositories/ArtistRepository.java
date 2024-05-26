package com.jovisco.quarkus.panache.repositories;

import java.util.List;

import com.jovisco.quarkus.jdbc.Artist;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

    public List<Artist> listAllSorted() {
        return this.listAll(Sort.descending("name"));
    }
}
