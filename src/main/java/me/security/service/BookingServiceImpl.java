package me.security.service;


import me.security.model.dto.BookingDto;
import me.security.model.entity.BookingEntity;
import me.security.model.mapper.BookingMapper;
import me.security.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository registrationRepository;

    @Autowired
    private BookingMapper registrationMapper;

    @Override
    public ResponseEntity<?> getAllBookings(){
        List<BookingEntity> allUsers = registrationRepository.findAll();
        List<BookingDto> allUsersList = registrationMapper.toDtoList(allUsers);
        return new ResponseEntity<>(allUsersList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> bookingRegistration(BookingEntity registration){
        Optional<BookingEntity> existingEmail = registrationRepository.findByEmail(registration.getEmail());
        if(existingEmail.isPresent()){
            return new ResponseEntity<>("Email Already Exists.", HttpStatus.BAD_REQUEST);
        }else {
            BookingEntity save = registrationRepository.save(registration);
            BookingDto dto = registrationMapper.toDto(save);
            return ResponseEntity.ok("User added successfully");
        }
    }

}
