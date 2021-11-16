package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.Reservations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends CrudRepository <Reservations, Integer> {

    Optional<Reservations> findByID(long id);

    @Query("SELECT r FROM Reservations r WHERE r.user.ID = :userId")
    public List<Reservations> getAllByUserId(@Param("userId") long userId);

}
