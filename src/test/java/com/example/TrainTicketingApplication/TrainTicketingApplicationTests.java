package com.example.TrainTicketingApplication;

import com.example.TrainTicketingApplication.Model.Ticket;
import com.example.TrainTicketingApplication.Model.User;
import com.example.TrainTicketingApplication.Service.TrainAPIs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainTicketingApplicationTests {

	@Test
    void testPurchaseTicket() {
        TrainAPIs api = new TrainAPIs();
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = api.purchaseTicket(user);

        assertNotNull(ticket);
        assertEquals("London", ticket.getFrom());
        assertEquals("France", ticket.getTo());
        assertEquals(20.0, ticket.getPrice());
        assertEquals(user, ticket.getUser());
    }

	@Test
    void testGetTicket() {
        TrainAPIs api = new TrainAPIs();
        User user = new User("Jane", "Doe", "jane.doe@example.com");
        api.purchaseTicket(user);
        Ticket ticket = api.getTicket("jane.doe@example.com");

        assertNotNull(ticket);
        assertEquals(user, ticket.getUser());
    }

}
