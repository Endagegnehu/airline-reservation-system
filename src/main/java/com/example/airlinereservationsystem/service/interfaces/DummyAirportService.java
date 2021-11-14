package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.DummyAirport;

import java.util.List;

public interface DummyAirportService {
    public List<DummyAirport> findAll();
    public DummyAirport findById(Long id);
    public void addAirport(DummyAirport flight);
}
