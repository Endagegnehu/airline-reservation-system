package com.example.airlinereservationsystem;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.repository.AirlineRepository;
import com.example.airlinereservationsystem.repository.AirportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirlineReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineReservationSystemApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner run (AirlineRepository airlineRepository, AirportRepository airportRepository) {
        return args->{
            Airport airport = new Airport();
            airport.setName("JFK NY Airport");
            airport.setCode("JFK");
            //airport.setAddress(new Address(null,"street","city","state","010000"));

            Airline airline = new Airline();
            airline.setHistory("bla bla bla history");
            airline.setName("MyAirlines");
            airline.setCode("M01");

            airlineRepository.save(airline);
            airportRepository.save(airport);

        };
    }
}
