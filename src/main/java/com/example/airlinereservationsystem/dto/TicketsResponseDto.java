package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketsResponseDto {
    private User user;
    private Long reservationId;
    private List<ModifiedTicket > tickets = new ArrayList<>();
    ;

    public void setTickets(ModifiedTicket ticket){
        tickets.add(ticket);
    }
}
