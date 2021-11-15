package com.example.airlinereservationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "flight_number")
    private Long number;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Airline.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airline airline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Airport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airport departureAirport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Airport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airport arrivalAirport;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    public Flight(Long number, Integer numberOfSeats, Airline airline, Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
