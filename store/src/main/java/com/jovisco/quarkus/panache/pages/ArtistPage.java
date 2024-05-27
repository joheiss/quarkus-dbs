package com.jovisco.quarkus.panache.pages;

import java.util.List;

import com.jovisco.quarkus.jdbc.Artist;
import com.jovisco.quarkus.panache.repositories.ArtistRepository;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/page/artists")
@Produces(MediaType.TEXT_HTML)
public class ArtistPage {

    @Inject
    ArtistRepository artistRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance artist(Artist artist);

        public static native TemplateInstance artists(List<Artist> artists);
    }

    @GET
    public TemplateInstance showAll() {

        return Templates.artists(artistRepository.listAll());
    }

    @GET
    @Path("{id}")
    public TemplateInstance showById(@PathParam("id") Long id) {

        return Templates.artist(artistRepository.findById(id));
    }

}
