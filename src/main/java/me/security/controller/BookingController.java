package me.security.controller;

import me.security.model.entity.BookingEntity;
import me.security.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    private String healthcheck(){
        return "Healthy";
    }

    @GetMapping("/abc")
    private String hhhh(){
        return "Hellu from role";
    }

    @GetMapping("/getAllBookings")
    public ResponseEntity<?> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/bookAppointment")
    public ResponseEntity<?> registerBooking(@RequestBody BookingEntity bookingEntity){
        return new ResponseEntity<>(bookingService.bookingRegistration(bookingEntity), HttpStatusCode.valueOf(200));
    }
}
