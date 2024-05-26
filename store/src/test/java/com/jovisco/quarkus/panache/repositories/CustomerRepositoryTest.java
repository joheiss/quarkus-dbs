package com.jovisco.quarkus.panache.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.jovisco.quarkus.jpa.Customer;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    @TestTransaction
    void shouldCreateAndFindACustomer() {

        var customer = Customer.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .build();

        customerRepository.persist(customer);
        assertNotNull(customer.getId());

        var found = customerRepository.findById(customer.getId());
        assertEquals(found.getFirstName(), "firstName");

        found = customerRepository.listAllByFirstName("firstName").getFirst();
        assertEquals(found.getFirstName(), "firstName");
    }
}
