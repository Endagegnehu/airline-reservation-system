package com.example.airlinereservationsystem.service.interfaces;

import javax.transaction.Transactional;

import com.example.airlinereservationsystem.domain.Reservations;
import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Tickets;

import java.util.List;

public interface TicketsService {
    Tickets addTicket(Tickets ticket) ;
    public List<Tickets> getTickets(Long reservationId, Long userId);

}
