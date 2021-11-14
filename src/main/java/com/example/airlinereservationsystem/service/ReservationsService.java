package com.example.airlinereservationsystem.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Reservations;
@Service
@Transactional
public interface ReservationsService {
      void addReservation(Reservations reservation) ;

	

}
