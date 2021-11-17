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
    @Query("SELECT DISTINCT f FROM Flight f WHERE f.departureAirport.code = :departure and f.arrivalAirport.code = :destination")
    List<Flight> findAllByAirports(@Param("departure") String departure, @Param("destination") String destination);

    @Query("SELECT DISTINCT f FROM Flight f JOIN f.departureAirport ap where ap.code = :code")
    List<Flight> getAllFlightByDepartureAirportCode(@Param("code") String code);

    @Query("SELECT DISTINCT f FROM Flight f JOIN f.airline al where al.code = :code")
    List<Flight> findAllByAirlineCode(@Param("code") String code);

    @Query("SELECT DISTINCT f FROM Flight f JOIN f.airline al JOIN f.departureAirport da where al.code = :airlineCode and da.code = :airportCode")
    List<Flight> findAllByDepartureAirportCodeAndAirlineCode(@Param("airlineCode") String airlineCode, @Param("airportCode") String airportCode);

}
