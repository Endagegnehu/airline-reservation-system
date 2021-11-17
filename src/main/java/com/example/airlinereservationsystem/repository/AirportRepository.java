package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Airport;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>, PagingAndSortingRepository<Airport, Long> {
    Airport findByCode(String code);

    void deleteByCode(String code);

}
