package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface FlightRespository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.code = :departure and f.arrivalAirport.code = :destination")
    public List<Flight> findSomeByAirports(@Param("departure") String departure, @Param("destination") String destination);
}
