package com.example.airlinereservationsystem.service.interfaces;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.User;
@Service
@Transactional
public interface ReservationsService {
      void addReservation(Reservations reservation) ;
      Optional<Reservations> findReservationsByID(long id);

	

}
