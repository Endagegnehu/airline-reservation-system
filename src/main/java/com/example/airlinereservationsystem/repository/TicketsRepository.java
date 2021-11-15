package com.example.airlinereservationsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.Tickets;


@Repository

public interface TicketsRepository  extends CrudRepository <Tickets, Integer>{


}
