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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DummyAirline.class)
    @JsonIdentityReference(alwaysAsId = true)
    private DummyAirline dummyAirline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DummyAirport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private DummyAirport departureDummyAirport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DummyAirport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private DummyAirport arrivalDummyAirport;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    public Flight(Long number, Integer numberOfSeats, DummyAirline dummyAirline, DummyAirport departureDummyAirport, DummyAirport arrivalDummyAirport, LocalTime departureTime, LocalTime arrivalTime) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.dummyAirline = dummyAirline;
        this.departureDummyAirport = departureDummyAirport;
        this.arrivalDummyAirport = arrivalDummyAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
