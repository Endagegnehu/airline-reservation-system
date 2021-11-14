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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AirlineDummy.class)
    @JsonIdentityReference(alwaysAsId = true)
    private AirlineDummy airlineDummy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AirportDummy.class)
    @JsonIdentityReference(alwaysAsId = true)
    private AirportDummy departureAirportDummy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AirportDummy.class)
    @JsonIdentityReference(alwaysAsId = true)
    private AirportDummy arrivalAirportDummy;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    public Flight(Long number, Integer numberOfSeats, AirlineDummy airlineDummy, AirportDummy departureAirportDummy, AirportDummy arrivalAirportDummy, LocalTime departureTime, LocalTime arrivalTime) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.airlineDummy = airlineDummy;
        this.departureAirportDummy = departureAirportDummy;
        this.arrivalAirportDummy = arrivalAirportDummy;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
