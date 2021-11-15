package com.example.airlinereservationsystem.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Tickets;

@Service
@Transactional
public interface TicketsService {
    void addTicket(Tickets ticket) ;


}
