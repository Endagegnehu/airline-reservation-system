package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.*;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.exception.ResourceNotFoundException;
import com.example.airlinereservationsystem.repository.FlightRespository;
import com.example.airlinereservationsystem.service.interfaces.AirlineService;
import com.example.airlinereservationsystem.service.interfaces.AirportService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRespository flightRespository;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Flight> findAll() {
        return flightRespository.findAll();
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return flightRespository.findAll(pageable);
    }

    @Override
    public Flight findById(Long id) {
        return flightRespository.findById(id).orElseThrow(() ->new ResourceNotFoundException( "Flight with id " + id + " not found"));
    }

    @Override
    public Flight addFlight(FlightDto flightDto) {
        Airline airline = airlineService.getAirlineById(flightDto.getAirline());
        Airport departureAirport = airportService.getAirportById(flightDto.getDepartureAirport());
        Airport arrivalAirport = airportService.getAirportById(flightDto.getArrivalAirport());
        if (airline != null && departureAirport != null && arrivalAirport != null ) {
            Flight flight = modelMapper.map(flightDto, Flight.class);
            flight.setAirline(airline);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureAirport(departureAirport);
            return flightRespository.save(flight);
        } else {
             throw new ResourceNotFoundException( "child entities are not found to create a flight");
        }
    }

    @Override
    public List<Flight> findSomeByAirports(String departure, String destination) {
        return flightRespository.findSomeByAirports(departure, destination);
    }

    @Override
    public Flight updateFlightProperty(Long id, FlightDto flightDto) {
        Flight flight  = flightRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("flight with id " + id + " not found for update"));
        Airline airline = flightDto.getAirline() != null ?  airlineService.getAirlineById(flightDto.getAirline()) : null;
        Airport departureAirport = flightDto.getDepartureAirport() != null ? airportService.getAirportById(flightDto.getDepartureAirport()) : null;
        Airport arrivalAirport = flightDto.getArrivalAirport() != null ?  airportService.getAirportById(flightDto.getArrivalAirport()) : null;

        boolean nonNull = Stream.of(flightDto.getNumber(),flightDto.getNumberOfSeats(), flightDto.getAirline(),
                        flightDto.getDepartureAirport(), flightDto.getArrivalAirport(),
                        flightDto.getDepartureTime(), flightDto.getArrivalTime())
                        .allMatch(Objects::nonNull);

        if (!nonNull)
            throw new ResourceNotFoundException("Null entities found");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(flightDto, flight);
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flightRespository.flush();
        return flight;
    }

    @Override
    public boolean removeFlight(Long id) {
        boolean isFound = flightRespository.existsById(id);
        if (isFound){
            flightRespository.deleteById(id);
            return isFound;
        }else{
            throw new ResourceNotFoundException("flight with id " + id + " not found for deletion");
        }
    }
}
