package com.jovisco.quarkus.panache.pages;

import java.util.List;

import com.jovisco.quarkus.jdbc.Artist;
import com.jovisco.quarkus.panache.repositories.ArtistRepository;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/page/artists")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class ArtistPage {

    @Inject
    ArtistRepository artistRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance artist(Artist artist);

        public static native TemplateInstance artists(List<Artist> artists);
    }

    @GET
    @Path("{id}")
    public TemplateInstance showArtistById(@PathParam("id") Long id) {

      log.info("*** showById" + String.valueOf(id) + " ***");

      var artist = artistRepository.findById(id);
      log.info("*** Artist found: " + artist.getName());

      return Templates.artist(artist)
        .data("query", null)
        .data("sort", null)
        .data("pageIndex", null)
        .data("pageSize", null);
    }

    @GET
    public TemplateInstance showAllArtists(
        @QueryParam("query") String query,
        @QueryParam("sort") @DefaultValue("id") String sort, 
        @QueryParam("page") @DefaultValue("0") Integer pageIndex, 
        @QueryParam("size") @DefaultValue("1000") Integer pageSize
    ) {

      log.info("*** showAll ***");

      return Templates.artists(artistRepository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
        .data("query", query)
        .data("sort", sort)
        .data("pageIndex", pageIndex)
        .data("pageSize", pageSize);
    }
}
