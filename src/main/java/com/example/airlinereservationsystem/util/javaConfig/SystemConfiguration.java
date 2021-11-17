package com.example.airlinereservationsystem.util.javaConfig;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.Airline;
import com.example.airlinereservationsystem.domain.Airport;
import com.example.airlinereservationsystem.repository.AirlineRepository;
import com.example.airlinereservationsystem.repository.AirportRepository;
import com.example.airlinereservationsystem.repository.FlightRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class SystemConfiguration {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("5-10");

        return factory;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
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

            airlineRepository.save(airline);
            airportRepository.save(airport);

        };
    }
}
