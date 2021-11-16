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
import org.springframework.context.annotation.ComponentScan;
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
//
//    @Bean
//    CommandLineRunner run (AirlineRepository airlineRepository, AirportRepository airportRepository, FlightRespository flightRespository) {
//        return args->{
//            Airport airport = new Airport();
//            airport.setName("JFK NY Airport");
//            airport.setCode("JFK");
//
//            Address address = new Address();
//            address.setCity("City");
//            address.setState("State");
//            address.setStreet("Street");
//            address.setZipCode("ZipCode");
////            addressRepository.save(address);
//            airport.setAddress(address);
//
//            Airline airline = new Airline();
//            airline.setHistory("bla bla bla history");
//            airline.setName("MyAirlines");
//            airline.setCode("M01");
//
//            airlineRepository.save(airline);
//            airportRepository.save(airport);
//
//        };
//    }
}
