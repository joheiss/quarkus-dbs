package com.jovisco.quarkus.panache.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jovisco.quarkus.panache.model.Publisher;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityNotFoundException;

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
        found = Publisher.findOneByName("name").orElseThrow(EntityNotFoundException::new);
        assertEquals(found.getName(), "name");
        var size = Publisher.listAllContainingName("ame").size();
        assertTrue(size >= 0);

        // count publishers
        var count = Publisher.count();
        assertTrue(count > 0);
        assertEquals(count, Publisher.listAll().size());
    }
}
