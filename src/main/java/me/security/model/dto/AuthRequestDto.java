package me.security.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
}
