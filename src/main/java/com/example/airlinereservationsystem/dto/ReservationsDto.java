package com.example.airlinereservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationsDto {
    private String flightInstanceId;
    private String PassangerId;

}
