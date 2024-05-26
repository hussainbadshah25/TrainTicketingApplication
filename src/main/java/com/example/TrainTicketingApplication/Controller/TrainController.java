package com.example.TrainTicketingApplication.Controller;

import com.example.TrainTicketingApplication.Model.User;
import com.example.TrainTicketingApplication.Model.Ticket;
import com.example.TrainTicketingApplication.Service.TrainAPIs;

//This line imports several annotations from the Spring Web MVC framework.
import org.springframework.web.bind.annotation.*;
import java.util.List;

//This annotation indicates that the class is a RESTful web service controller
@RestController 

//This annotation maps the class to the /api/train URL.
@RequestMapping("/api/train")
public class TrainController {
    private final TrainAPIs apis = new TrainAPIs();
    
    //This annotation maps the purchaseTicket method to the /api/train/purchase URL and handles HTTP POST requests.
    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody User user) {
        return apis.purchaseTicket(user);
    }
    
    //This annotation maps the getTicket method to the /api/train/ticket/{email} URL and handles HTTP GET requests.
    @GetMapping("/ticket/{email}")
    public Ticket getTicket(@PathVariable String email) {
        return apis.getTicket(email);
    }

    //This annotation maps the getUsersBySection method to the /api/train/users URL and handles HTTP GET requests.
    @GetMapping("/users")
    public List<User> getUsersBySection(@RequestParam char section) {
        return apis.getUsersBySection(section);
    }

    //This annotation maps the removeUser method to the /api/train/user/{email} URL and handles HTTP DELETE requests.
    @DeleteMapping("/user/{email}")
    public void removeUser(@PathVariable String email) {
        apis.removeUser(email);
    }

    //This annotation maps the modifySeat method to the /api/train/user/{email}/seat URL and handles HTTP PUT requests.
    @PutMapping("/user/{email}/seat")
    public Ticket modifySeat(@PathVariable String email, @RequestParam String newSeat) {
        return apis.modifySeat(email, newSeat);
    }
}
