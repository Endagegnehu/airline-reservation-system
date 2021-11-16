package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.Tickets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketsDto {
    List<Tickets> tickets;
}
