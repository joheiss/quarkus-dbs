package com.jovisco.quarkus.panache.repositories;

import java.util.List;

import com.jovisco.quarkus.jpa.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> listAllByFirstName(String firstName) {
        return list("firstName = ?1", Sort.by("lastName"), firstName);
    }
}
