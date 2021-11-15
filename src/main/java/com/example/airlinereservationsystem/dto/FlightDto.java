package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
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
    private Airline Airline;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

}
