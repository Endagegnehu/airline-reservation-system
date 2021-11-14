package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,Long> {

    Airline findByCode(String code);

    void deleteByCode(String code);
}
