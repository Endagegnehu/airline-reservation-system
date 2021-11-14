package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.DummyAirline;

import java.util.List;

public interface DummyAirlineService {
    public List<DummyAirline> findAll();
    public DummyAirline findById(Long id);
    public void addAirline(DummyAirline flight);
}
