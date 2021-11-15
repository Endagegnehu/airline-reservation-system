package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.repository.FlightInstanceRepository;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FlightInstanceServiceImpl implements FlightInstanceService {

    @Autowired
    private FlightInstanceRepository instanceRepository;

    @Override
    public Page<FlightInstance> findAll(Pageable pageable) {
        return instanceRepository.findAll(pageable);
    }

    @Override
    public Page<FlightInstance> findAllPerFlight(Long id, Pageable pageable) {
        return instanceRepository.findAllPerFlight(id, pageable);
    }
}
