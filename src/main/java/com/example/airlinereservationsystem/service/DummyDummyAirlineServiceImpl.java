package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.DummyAirline;
import com.example.airlinereservationsystem.repository.DummyAirlineRespository;
import com.example.airlinereservationsystem.service.interfaces.DummyAirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DummyDummyAirlineServiceImpl implements DummyAirlineService {
    @Autowired
    private DummyAirlineRespository airlineRepository;

    @Override
    public List<DummyAirline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public DummyAirline findById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    @Override
    public void addAirline(DummyAirline dummyAirline) {
        airlineRepository.save(dummyAirline);
    }
}
