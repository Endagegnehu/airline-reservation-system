package com.example.airlinereservationsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "flight_instances")
public class FlightInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "departure_date")
    @NonNull
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    @NonNull
    private LocalDate arrivalDate;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "flight_id")
    private Flight flight;


}