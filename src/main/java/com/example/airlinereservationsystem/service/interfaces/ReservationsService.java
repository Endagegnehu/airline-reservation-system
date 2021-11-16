package com.example.airlinereservationsystem.service.interfaces;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.airlinereservationsystem.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.User;
@Service
@Transactional
public interface ReservationsService {
      Reservations addReservation(Reservations reservation) ;
      Optional<Reservations> findReservationsByID(long id);
      public List<Reservations> getAllByUserId(long userId);

	

}
