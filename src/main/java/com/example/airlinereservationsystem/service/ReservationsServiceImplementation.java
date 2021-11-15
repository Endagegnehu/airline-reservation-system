package com.example.airlinereservationsystem.service;

import java.util.Optional;

import com.example.airlinereservationsystem.service.interfaces.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.repository.ReservationsRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationsServiceImplementation implements ReservationsService {
    @Autowired
    ReservationsRepository reservationsRepository;
	@Override
	public  void addReservation(Reservations reservation) {
		reservationsRepository.save( reservation);

	}
	@Override
	public Optional<Reservations> findReservationsByID(long id) {
        return reservationsRepository.findByID(id);

	}

}
