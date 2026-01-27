package me.security.controller;

import me.security.model.dto.ContactDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @PostMapping("/us")
    public ResponseEntity<?> contactUs(ContactDto contactDto){
        return new ResponseEntity<>("response", HttpStatusCode.valueOf(200));
    }
}
