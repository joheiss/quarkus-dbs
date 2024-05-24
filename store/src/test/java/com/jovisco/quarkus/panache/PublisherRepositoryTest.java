package com.jovisco.quarkus.panache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    void shouldCreateAndFindAPublisher() {

        var publisher = Publisher.builder()
            .name("name")
            .build();

        Publisher.persist(publisher);
        assertNotNull(publisher.id);

       Publisher found = Publisher.findById(publisher.id);
        assertEquals(found.getName(), "name");
    }
}
