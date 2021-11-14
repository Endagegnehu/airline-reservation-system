package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FlightInstanceServiceImpl implements FlightInstanceService {

    @Autowired
    private FlightInstanceServiceImpl flightInstanceService;
}
