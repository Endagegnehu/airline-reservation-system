package com.example.airlinereservationsystem;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.repository.AddressRepository;
import com.example.airlinereservationsystem.repository.AirlineRepository;
import com.example.airlinereservationsystem.repository.AirportRepository;
import com.example.airlinereservationsystem.repository.FlightRespository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;

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
    CommandLineRunner run (AirlineRepository airlineRepository, AirportRepository airportRepository, FlightRespository flightRespository) {
        return args->{
            Airport airport = new Airport();
            airport.setName("JFK NY Airport");
            airport.setCode("JFK");

            Address address = new Address();
            address.setCity("City");
            address.setState("State");
            address.setStreet("Street");
            address.setZipCode("ZipCode");
//            addressRepository.save(address);
            airport.setAddress(address);

            Airline airline = new Airline();
            airline.setHistory("bla bla bla history");
            airline.setName("MyAirlines");
            airline.setCode("M01");

            Airline airline1 = new Airline(null,"AL1","Airline1","Airline1");
            Airline airline2 = new Airline(null,"AL2","Airline2","Airline2");
            Airline airline3 = new Airline(null,"AL3","Airline3","Airline3");
            Airline airline4 = new Airline(null,"AL4","Airline4","Airline4");
            Airline airline5 = new Airline(null,"AL5","Airline5","Airline5");

            airlineRepository.save(airline);
            airlineRepository.save(airline1);
            airlineRepository.save(airline2);
            airlineRepository.save(airline3);
            airlineRepository.save(airline4);
            airlineRepository.save(airline5);
            airportRepository.save(airport);

        };
    }
}
