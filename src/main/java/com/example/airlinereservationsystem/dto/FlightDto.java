package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.DummyAirline;
import com.example.airlinereservationsystem.domain.DummyAirport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    private Long id;
    private Long number;
    private Integer numberOfSeats;
    private DummyAirline dummyAirline;
    private DummyAirport departureDummyAirport;
    private DummyAirport arrivalDummyAirport;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

}
