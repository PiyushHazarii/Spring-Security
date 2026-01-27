package me.security.model.mapper;


import me.security.model.dto.BookingDto;
import me.security.model.entity.BookingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public static BookingEntity toEntity(BookingDto dto) {
        if (dto == null) return null;

        BookingEntity registration = new BookingEntity();
        registration.setId(dto.getId());
        registration.setName(dto.getName());
        registration.setEmail(dto.getEmail());
        registration.setPhone(dto.getPhone());

        return registration;
    }

    public static BookingDto toDto(BookingEntity registration) {
        if (registration == null) return null;

        BookingDto dto = new BookingDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setPhone(registration.getPhone());

        return dto;
    }

    public static List<BookingDto> toDtoList(List<BookingEntity> registrations) {
        if (registrations == null) return null;

        return registrations.stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }
}
