package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Reservations;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationsRepository extends CrudRepository <Reservations, Integer> {

    Optional<Reservations> findByID(long id);

}
