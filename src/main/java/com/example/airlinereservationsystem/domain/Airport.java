package com.example.airlinereservationsystem.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id @GeneratedValue
    private  Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Address address;
}
