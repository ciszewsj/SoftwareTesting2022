package com.example.testowanieoprogramowania.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    private Date birth;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String country;

    @Column(length = 6)
    private String postCode;

    private UserRole role;

    public enum UserRole {
        USER, ADMIN
    }
}
