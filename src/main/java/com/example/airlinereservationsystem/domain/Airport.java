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
    private String code;
    private String name;
//    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @OneToOne
    private Address address;
}
