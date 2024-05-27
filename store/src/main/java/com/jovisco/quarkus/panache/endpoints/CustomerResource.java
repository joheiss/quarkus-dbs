package com.jovisco.quarkus.panache.endpoints;

import java.util.List;

import com.jovisco.quarkus.jpa.Customer;
import com.jovisco.quarkus.panache.repositories.CustomerRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @GET
    public List<Customer> listAll() {
        return customerRepository.listAll();
    }

    @GET
    @Path("/{id: \\d+}")
    public Customer getById(@PathParam("id") Long id) {
        return customerRepository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }
}
