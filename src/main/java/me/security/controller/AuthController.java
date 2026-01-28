package me.security.controller;

import me.security.model.dto.AuthRequestDto;
import me.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequestDto authRequestDto) {
        try {
            String token=null;
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
                    authRequestDto.getPassword()));
            if(authenticate != null){
                token = jwtUtil.generateToken(authRequestDto.getUsername());
            }

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
