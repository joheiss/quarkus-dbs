package com.jovisco.quarkus.panache.endpoints;

import java.util.List;

import com.jovisco.quarkus.panache.model.PurchaseOrder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PurchaseOrderResource {

    @GET
    public List<PurchaseOrder> listAll() {
        return PurchaseOrder.listAll();
    }

    @GET
    @Path("/{id: \\d+}")
    public PurchaseOrder getById(@PathParam("id") Long id) {
        return (PurchaseOrder) PurchaseOrder.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

}
