package com.example.airlinereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> role = new ArrayList<>();
}
