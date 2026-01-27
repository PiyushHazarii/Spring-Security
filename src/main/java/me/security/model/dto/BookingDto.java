package me.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookingDto {

    private Long id;

    private String name;
    private String phone;
    private String email;
    private String message;
    private List<String> serviceType;
    private String gender;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
