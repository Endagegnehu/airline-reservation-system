package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.repository.AirportRepository;
import com.example.airlinereservationsystem.service.interfaces.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AirportServiceImpl implements AirportService {

    private final AirportRepository repository;

    @Override
    public List<Airport> getAllAirports() {
        return repository.findAll();
    }


    @Override
    public Airport getAirportByCode(String code) {
        return repository.findByCode(code);
    }

    @Override
    public Airport getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void addAirport(Airport airport) {
        repository.save(airport);
    }

    @Override
    public void updateAirport(Airport airport) {
        Optional<Airport> optionalAirport = Optional.ofNullable(repository.findByCode(airport.getCode()));
        if (!optionalAirport.isPresent()){
            throw new IllegalStateException("Airport with given code does not exist (code: " + airport.getCode()+")");
        }
        optionalAirport.get().setAddress(airport.getAddress());
        optionalAirport.get().setCode(airport.getCode());
        optionalAirport.get().setName(airport.getName());
    }

    @Override
    public void deleteAirport(String code) {
        Optional<Airport> optionalAirport = Optional.ofNullable(repository.findByCode(code));
        if (!optionalAirport.isPresent()){
            throw new IllegalStateException("Airport with given code does not exist (code: " + code+")");
        }
        repository.deleteByCode(code);
    }

    @Override
    public Page<Airport> getAllAirportsPages(Pageable page) {
        return repository.findAll(page);
    }
}
