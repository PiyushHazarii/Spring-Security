package me.security.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_us")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String query;
}
