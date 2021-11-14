package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.AirlineDummy;
import com.example.airlinereservationsystem.domain.AirportDummy;
import com.example.airlinereservationsystem.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Arrays;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataSeedRunnableRepository implements CommandLineRunner {
    @Autowired
    FlightInstanceRepository flightInstanceRepository;

    @Autowired
    FlightRespository flightRespository;

    @Override
    public void run(String... args) throws Exception {
        Flight flight = new Flight(120012L, 66, new AirlineDummy(), new AirportDummy(), new AirportDummy(), LocalTime.of(12,00,00),LocalTime.of(12,00,00 ));
        flightRespository.save(flight);

        Flight flight1 = new Flight(51210L, 55, new AirlineDummy(), new AirportDummy(), new AirportDummy(), LocalTime.of(12,00,00),LocalTime.of(12,00,00 ));
        flightRespository.save(flight);

        Flight flight2 = new Flight(45762L, 44, new AirlineDummy(), new AirportDummy(), new AirportDummy(), LocalTime.of(12,00,00),LocalTime.of(12,00,00 ));
        flightRespository.save(flight);

        Flight flight3 = new Flight(156720L, 33, new AirlineDummy(), new AirportDummy(), new AirportDummy(), LocalTime.of(12,00,00),LocalTime.of(12,00,00 ));
        flightRespository.save(flight);

        Flight flight4 = new Flight(205670L, 77, new AirlineDummy(), new AirportDummy(), new AirportDummy(), LocalTime.of(12,00,00),LocalTime.of(12,00,00 ));

        flightRespository.saveAll(Arrays.asList(flight, flight1, flight2, flight3, flight4));
    }
}
