package me.security.service;

import me.security.model.entity.BookingEntity;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<?> getAllBookings();
    ResponseEntity<?> bookingRegistration(BookingEntity registration);


}
