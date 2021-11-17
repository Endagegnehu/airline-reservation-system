package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.*;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.util.exception.ResourceNotFoundException;
import com.example.airlinereservationsystem.repository.FlightRespository;
import com.example.airlinereservationsystem.service.interfaces.AirlineService;
import com.example.airlinereservationsystem.service.interfaces.AirportService;
import com.example.airlinereservationsystem.service.interfaces.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Page<Flight> flights = flightRespository.findAll(pageable);
        if (flights.getSize() == 0 )
            throw new ResourceNotFoundException("No flights found");
        return flights;
    }

    @Override
    public Flight findById(Long id) {
        return flightRespository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight with id " + id + " not found"));
    }

    @Override
    public Flight addFlight(FlightDto flightDto) {
        Airline airline = airlineService.getAirlineById(flightDto.getAirline());
        Airport departureAirport = airportService.getById(flightDto.getDepartureAirport());
        Airport arrivalAirport = airportService.getById(flightDto.getArrivalAirport());
        Flight flight = modelMapper.map(flightDto, Flight.class);
        flight.setAirline(airline);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        return flightRespository.save(flight);
    }

    @Override
    public List<Flight> findAllByAirports(String departure, String destination) {

        List<Flight> flights = flightRespository.findAllByAirports(departure, destination);

        if (flights.isEmpty())
            throw new ResourceNotFoundException("Flight with the departure " + departure + " and destination " + destination + " not found");

        return flights;
    }

    @Override
    public List<Flight> findAllByAirlineCode(String code) {

        List<Flight> flights = flightRespository.findAllByAirlineCode(code);

        if (flights.isEmpty())
            throw new ResourceNotFoundException("Flight with airline code " + code + " not found");

        return flights;
    }

    @Override
    public Flight updateFlight(Long id, FlightDto flightDto) {
        Flight flight = flightRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("flight with id " + id + " not found for update"));
        Airline airline = airlineService.getAirlineById(flightDto.getAirline());
        Airport arrivalAirport = airportService.getById(flightDto.getArrivalAirport());
        Airport departureAirport = airportService.getById(flightDto.getDepartureAirport());

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(flightDto, flight);
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flightRespository.flush();
        return flight;
    }

    @Override
    public void removeFlight(Long id) {
        Flight flight = flightRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("flight with id " + id + " not found for deletion"));

        flightRespository.delete(flight);
    }

    @Override
    public List<Flight> findAllByDepartureAirportCodeAndAirlineCode(String airlineCode, String airportCode){

        List<Flight> flights =  flightRespository.findAllByDepartureAirportCodeAndAirlineCode(airlineCode,airportCode);

        if (flights.isEmpty())
            throw new ResourceNotFoundException("Flights with Departure airport/airline with code " + airportCode +"/" + airlineCode + " not found");

        return flights;
    }

    @Override
    public List<Flight> getAllFlightByDepartureAirportCode(String code){

        List<Flight> flights =  flightRespository.getAllFlightByDepartureAirportCode(code);

        if (flights.isEmpty())
            throw new ResourceNotFoundException("Flights with Departure airport with code " + code + " not found");

        return flights;
    }

}
