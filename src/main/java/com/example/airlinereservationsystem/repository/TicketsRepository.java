package com.example.airlinereservationsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.Tickets;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(propagation = Propagation.REQUIRED)

public interface TicketsRepository  extends CrudRepository <Tickets, Integer>{


    @Query("SELECT distinct  t FROM Tickets t WHERE  t.reservation.ID = :id")
    public List<Tickets> getATicket(@Param("id") long id);

}
