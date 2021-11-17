package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.FlightInstance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifiedTicket {
    private long id;
    private String code;
    private String number;
    private FlightInstance flightInstance;

}
