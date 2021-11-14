package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.DummyAirport;
import com.example.airlinereservationsystem.repository.DummyAirportRespository;
import com.example.airlinereservationsystem.service.interfaces.DummyAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DummyDummyAirportServiceImpl implements DummyAirportService {
    @Autowired
    private DummyAirportRespository airportRespository;

    @Override
    public List<DummyAirport> findAll() {
        return airportRespository.findAll();
    }

    @Override
    public DummyAirport findById(Long id) {
        return airportRespository.findById(id).orElse(null);
    }

    @Override
    public void addAirport(DummyAirport dummyAirport) {
        airportRespository.save(dummyAirport);
    }
}
