package com.example.airlinereservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.repository.ReservationsRepository;

public class ReservationsServiceImplementation implements ReservationsService {
    @Autowired
    ReservationsRepository reservationsRepository;
	@Override
	public  void addReservation(Reservations reservation) {
		reservationsRepository.save( reservation);

	}

}
