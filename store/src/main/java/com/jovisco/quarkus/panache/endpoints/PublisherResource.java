package com.jovisco.quarkus.panache.endpoints;

import java.util.List;

import com.jovisco.quarkus.panache.model.Publisher;

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
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;

@Transactional(Transactional.TxType.SUPPORTS)
@Path("/api/v1/publishers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PublisherResource {

    @GET
    public List<Publisher> listAll() {
        return Publisher.listAll();
    }

    @GET
    @Path("/{id: \\d+}")
    public Publisher getById(@PathParam("id") Long id) {
        return (Publisher) Publisher.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response create(Publisher publisher, @Context UriInfo uriInfo) {

        Publisher.persist(publisher);

        var uriBuilder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @DELETE
    @Path("{id: \\d+}")
    public Response delete(@PathParam("id") Long id) {
        var deleted = Publisher.deleteById(id);

        var responseBuilder = deleted ? Response.noContent() : Response.status(Status.NOT_FOUND);
        return responseBuilder.build();
    }

}
