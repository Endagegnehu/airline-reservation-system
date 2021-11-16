package com.example.airlinereservationsystem.service.interfaces;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Tickets;

@Service
@Transactional
public interface TicketsService {
    Tickets addTicket(Tickets ticket) ;


}
