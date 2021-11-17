package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AirlineRepository extends JpaRepository<Airline,Long>, PagingAndSortingRepository<Airline, Long> {

    Airline findByCode(String code);

    void deleteByCode(String code);

}
