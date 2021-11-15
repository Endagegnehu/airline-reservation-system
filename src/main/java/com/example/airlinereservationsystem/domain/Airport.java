package com.example.airlinereservationsystem.domain;


import lombok.*;

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

    public Airport(String code) {
        this.code = code;
    }
}
