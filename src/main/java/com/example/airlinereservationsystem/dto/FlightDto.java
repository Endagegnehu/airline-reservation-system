package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.exception.ResourceNotFoundException;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {
    @NonNull
    private Long id;
    @NonNull
    private Long number;
    @NonNull
    private Integer numberOfSeats;
    @NonNull
    private Long airline;
    @NonNull
    private Long departureAirport;
    @NonNull
    private Long arrivalAirport;
    @NonNull
    private LocalTime departureTime;
    @NonNull
    private LocalTime arrivalTime;

}
