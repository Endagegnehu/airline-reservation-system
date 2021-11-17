package com.example.airlinereservationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "flight_number")
    private Long number;

    @NonNull
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "airline_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code", scope = Airline.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airline airline;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departure_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code", scope = Airport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airport departureAirport;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrival_airport_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code", scope = Airport.class)
    @JsonIdentityReference(alwaysAsId = true)
    private Airport arrivalAirport;

    @NonNull
    @Column(name = "departure_time")
    private LocalTime departureTime;

    @NonNull
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

}
