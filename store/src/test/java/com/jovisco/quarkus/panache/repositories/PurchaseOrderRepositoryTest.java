package com.jovisco.quarkus.panache.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.jovisco.quarkus.jdbc.Artist;
import com.jovisco.quarkus.jpa.Customer;
import com.jovisco.quarkus.panache.model.Book;
import com.jovisco.quarkus.panache.model.Language;
import com.jovisco.quarkus.panache.model.OrderLine;
import com.jovisco.quarkus.panache.model.Publisher;
import com.jovisco.quarkus.panache.model.PurchaseOrder;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

    @Inject
    CustomerRepository customerRepository;
    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPurchaseOrder() {
        long countCustomers = customerRepository.count();
        long countArtists = artistRepository.count();
        long countPublishers = Publisher.count();
        long countBooks = Book.count();
        long countPurchaseOrders = PurchaseOrder.count();
        long countOrderLines = OrderLine.count();

        // create a customer
        var customer = createCustomer();

        // create an artist
        var artist = createArtist();

        // creates a publisher
        var publisher = creatPublisher();

        // creates a book
        var book = createBook(artist, publisher);
        // persist the book with one publisher and one artist
        Book.persist(book);

        // create a purchase order with an order line
        var orderLine = createOrderLine(book);
        var purchaseOrder = createPurchaseOrder(customer, orderLine);
        // persist the purchase order and one order line
        PurchaseOrder.persist(purchaseOrder);

        // ... perform some tests
        assertNotNull(purchaseOrder.id);
        assertEquals(1, purchaseOrder.getOrderLines().size());

        assertEquals(countCustomers + 1, customerRepository.count());
        assertEquals(countArtists + 1, artistRepository.count());
        assertEquals(countPublishers + 1, Publisher.count());
        assertEquals(countBooks + 1, Book.count());
        assertEquals(countPurchaseOrders + 1, PurchaseOrder.count());
        assertEquals(countOrderLines + 1, OrderLine.count());

        // get the purchase order
        purchaseOrder = PurchaseOrder.findById(purchaseOrder.id);

        // delete the purchase order
        PurchaseOrder.deleteById(purchaseOrder.id);
        assertEquals(countCustomers + 1, customerRepository.count());
        assertEquals(countArtists + 1, artistRepository.count());
        assertEquals(countPublishers + 1, Publisher.count());
        assertEquals(countBooks + 1, Book.count());
        assertEquals(countPurchaseOrders, PurchaseOrder.count());
        assertEquals(countOrderLines, OrderLine.count());
    }

    private Customer createCustomer() {

        return Customer.builder()
                .firstName("first name")
                .lastName("last name")
                .email("email")
                .build();
    }

    private Artist createArtist() {

        return Artist.builder()
                .name("name")
                .bio("bio")
                .build();
    }

    private Publisher creatPublisher() {

        return Publisher.builder()
                .name("name")
                .build();
    }

    private Book createBook(Artist artist, Publisher publisher) {

        var book = new Book();
        book.setTitle("title");
        book.setDescription("description");
        book.setPrice(new BigDecimal(10));
        book.setIsbn("ISBN");
        book.setNumberOfPages(500);
        book.setPublishedAt(LocalDate.now().minusDays(100));
        book.setLanguage(Language.ENGLISH);

        // set the publisher and artist to the Book
        book.setPublisher(publisher);
        book.setArtist(artist);

        return book;
    }

    private OrderLine createOrderLine(Book book) {

        return OrderLine.builder()
                .item(book)
                .quantity(7)
                .build();
    }

    private PurchaseOrder createPurchaseOrder(Customer customer, OrderLine orderLine) {

        var purchaseOrder = PurchaseOrder.builder()
                .customer(customer)
                .build();

        purchaseOrder.addOrderLine(orderLine);

        return purchaseOrder;
    }
}
