package com.example.airlinereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    // todo add flight 

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FlightInstance> flightInstances = new ArrayList<>();

}
