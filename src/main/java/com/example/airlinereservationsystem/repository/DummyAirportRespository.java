package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.AirportDummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface DummyAirportRespository extends JpaRepository<AirportDummy, Long> {
}
