package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.repository.TicketsRepository;

@Service
public class TicketsServiceImplementation implements TicketsService {
	@Autowired
	TicketsRepository ticketsRepository;
	@Override
	public void addTicket(Tickets ticket) {
		ticketsRepository.save(ticket);
		
	}

}
