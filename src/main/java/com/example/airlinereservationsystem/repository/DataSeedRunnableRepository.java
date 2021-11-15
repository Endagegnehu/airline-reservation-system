package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.FlightInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataSeedRunnableRepository implements CommandLineRunner {
    @Autowired
    FlightInstanceRepository flightInstanceRepository;

    @Autowired
    FlightRespository flightRespository;

    @Override
    public void run(String... args) throws Exception {
        runForFlightAndFlightInstance();
    }

    private void  runForFlightAndFlightInstance(){
        /* Flights */
        List<Flight> flights = new ArrayList<>();
        flights.add( new Flight(120012L, 66, new Airline("AL-1", "Airline-name-1"), new Airport("AP1"), new Airport("AP4"), LocalTime.of(12,00,00),LocalTime.of(12,00,00 )));
        flights.add( new Flight(51210L, 55, new Airline("AL-2", "Airline-name-2"), new Airport("AP2"), new Airport("AP5"), LocalTime.of(12,00,00),LocalTime.of(12,00,00 )));
        flights.add( new Flight(45762L, 44, new Airline("AL-3", "Airline-name-3"), new Airport("AP3"), new Airport("AP6"), LocalTime.of(12,00,00),LocalTime.of(12,00,00 )));
        flights.add( new Flight(156720L, 33, new Airline("AL-4", "Airline-name-4"), new Airport("AP4"), new Airport("AP7"), LocalTime.of(12,00,00),LocalTime.of(12,00,00 )));
        flights.add( new Flight(205670L, 77, new Airline("AL-5", "Airline-name-5"), new Airport("AP5"), new Airport("AP8"), LocalTime.of(12,00,00),LocalTime.of(12,00,00 )));


        /* Flight Instances */
        // For flight0
        List<FlightInstance> flightInstances = new ArrayList<>();
        flightInstances.add(new FlightInstance(LocalDate.of(2020, 06, 8), LocalDate.of(2020, 06, 9), flights.get(0)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2), flights.get(0)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 6, 8), LocalDate.of(2020, 6, 8), flights.get(0)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 21), flights.get(0)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 12, 8), LocalDate.of(2020, 12, 9), flights.get(0)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 11, 8), LocalDate.of(2020, 11, 8), flights.get(0)));

        //for flight1
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 1, 8), LocalDate.of(2020, 1, 9), flights.get(1)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2), flights.get(1)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 3, 8), LocalDate.of(2020, 3, 8), flights.get(1)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 10, 20), LocalDate.of(2020, 10, 21), flights.get(1)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 12, 8), LocalDate.of(2020, 12, 9), flights.get(1)));
        flightInstances.add( new FlightInstance(LocalDate.of(2020, 11, 8), LocalDate.of(2020, 11, 8), flights.get(1)));


        flightRespository.saveAll(flights);
        flightInstanceRepository.saveAll(flightInstances);
    }
}
