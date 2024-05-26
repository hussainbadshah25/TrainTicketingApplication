package com.example.TrainTicketingApplication.Service;
import com.example.TrainTicketingApplication.Model.Ticket;
import com.example.TrainTicketingApplication.Model.User;
import com.example.TrainTicketingApplication.Exceptions.CustomExceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainAPIs {
    private static final double TICKET_PRICE = 20.0;
    private Map<String, Ticket> tickets = new HashMap<>();
    private int seatCounter = 1;

    public Ticket purchaseTicket(User user) {
        if (tickets.containsKey(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already has a ticket.");
        }

        String seat = allocateSeat();
        Ticket ticket = new Ticket("London", "France", user, TICKET_PRICE, seat);
        tickets.put(user.getEmail(), ticket);
        return ticket;
    }

    public Ticket getTicket(String email) {
        Ticket existingTicket = tickets.get(email);
        if(existingTicket == null)
        {
            throw new TicketNotFoundException("No ticket found with email id: " + email);    
        }
        return existingTicket;
    }

    public List<User> getUsersBySection(char section) {
        return tickets.values().stream()
                .filter(ticket -> ticket.getSeat().charAt(0) == section)
                .map(Ticket::getUser)
                .collect(Collectors.toList());
    }

    public String removeUser(String email) {
        Ticket ticket = tickets.get(email);
        if(ticket == null)
        {
            throw new TicketNotFoundException("No ticket found with email id: " + email);   
        }

        tickets.remove(email);
        return "Ticket deleted successfully";
    }

    public Ticket modifySeat(String email, String newSeat) {
        Ticket ticket = tickets.get(email);
        if(ticket == null)
        {
            throw new TicketNotFoundException("No ticket found with email id: " + email);   
        }
        else if(ticket.getSeat() == newSeat)
        {
            throw new SeatModifyException("New Seat already alocated to email id:" + email);
        }
        else{
            ticket.setSeat(newSeat);
            return ticket;
        }
        
        }
        
    private String allocateSeat() {
        char section;
        int seatNumber = (seatCounter + 1) / 2;
    
        if (seatCounter % 2 == 1) {
            section = 'A';
        } else {
            section = 'B';
        }
        seatCounter++;
        return section + String.valueOf(seatNumber);
    }
}