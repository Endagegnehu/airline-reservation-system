package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.repository.TicketsRepository;

import java.util.List;

@Service
public class TicketsServiceImplementation implements TicketsService {
	@Autowired
	TicketsRepository ticketsRepository;
	@Override
	public Tickets addTicket(Tickets ticket) {
		return ticketsRepository.save(ticket);
		
	}

	@Override
	public List<Tickets> getTickets(Long reservationId, Long userId) {
		return ticketsRepository.getTickets(reservationId, userId);
	}

}
