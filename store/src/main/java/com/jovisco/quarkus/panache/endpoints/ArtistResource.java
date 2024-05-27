package com.jovisco.quarkus.panache.endpoints;

import java.util.List;

import com.jovisco.quarkus.jdbc.Artist;
import com.jovisco.quarkus.panache.repositories.ArtistRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/api/v1/artists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
// @Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class ArtistResource {

    @Inject
    ArtistRepository artistRepository;

    @GET
    public List<Artist> listAll() {
        return artistRepository.listAll();
    }

    @GET
    @Path("/{id: \\d+}")
    public Artist getById(@PathParam("id") Long id) {
        return artistRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response create(Artist artist, @Context UriInfo uriInfo) {

        var exists = artistRepository.isPersistent(artist);
        if (exists) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        artistRepository.persist(artist);

        var uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @DELETE
    @Path("{id: \\d+}")
    public Response delete(@PathParam("id") Long id) {
        var deleted = artistRepository.deleteById(id);

        var responseBuilder = deleted ? Response.noContent() : Response.status(Status.NOT_FOUND);
        return responseBuilder.build();
    }
}
