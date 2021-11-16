package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.*;
import com.example.airlinereservationsystem.dto.ConfirmationDto;
import com.example.airlinereservationsystem.dto.FlightDto;
import com.example.airlinereservationsystem.dto.ReservationsDto;
import com.example.airlinereservationsystem.dto.TicketsDto;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import com.example.airlinereservationsystem.service.interfaces.ReservationsService;
import com.example.airlinereservationsystem.service.ReservationsServiceImplementation;
import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.ResponseHandler;
import com.example.airlinereservationsystem.util.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class ReservationsController {
    Logger logger = LoggerFactory.getLogger(TicketsController.class);
    @Autowired
    ReservationsService reservationsService;
    @Autowired
    UserService userService;
    @Autowired
    TicketsService ticketsService;

    @Autowired
    FlightInstanceService flightinstanceService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ReservationsServiceImplementation reservationsServiceImpl;



    @PostMapping("/reservations")
    public  ResponseEntity<?> addReservation(@RequestBody ReservationsDto reservationsDto){

        Reservations reservations = new Reservations();

        // get passanger user
        final Optional<User> user = userService.findUserByID(reservationsDto.getUserId());
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "));
        reservations.setUser(user.get());

        // get performed action user
        long [] ids = reservationsDto.getFlightInstanceIds();
        List<FlightInstance > flightInstances = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            final Optional<FlightInstance> flightInstance = flightinstanceService.findById(ids[i]);
            flightInstance.orElseThrow(() -> new UsernameNotFoundException("No reservation found: "));
            flightInstances.add(flightInstance.get());
        }
       reservations.setFlightInstances(flightInstances);

        Reservations respone = reservationsService.addReservation(reservations);
        if ( respone != null){
            return  ResponseHandler.respond("Successfully added a reservation!", HttpStatus.OK, respone);
        } else {
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reservations/confirm")
    public  ResponseEntity<?> confirmReservations(@RequestBody ConfirmationDto confirmationDto){
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(confirmationDto.getReservationId());
        reservation.orElseThrow(()-> new UsernameNotFoundException("No reservation found: "));
        Reservations reservationObj = reservation.get();
        List<FlightInstance> list = reservationObj.getFlightInstances();
        //TicketsDto finResponse = new TicketsDto();
        List<Tickets>  ticketsResponse = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            Tickets ticket = new Tickets();
            ticket.setReservation(reservationObj);
            ticket.setFlightInstance(list.get(i));
            ticket.setNumber(genrateRandomString("numeric"));
            ticket.setReservationCode(genrateRandomString("alpha"));
            ticketsResponse.add( ticketsService.addTicket(ticket));
        }
        //finResponse.setTickets(ticketsResponse);
        if ( ticketsResponse.size() != 0 ){
            return  ResponseHandler.respond("Successfully added a ticket!", HttpStatus.OK);
        } else {
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);
        }

    }

    public static String genrateRandomString(String type) {
        String SALTCHARS = "";
        int size  = 0;
        if(type.equals("alpha")){
            SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            size = 6;
        }
        else{
            SALTCHARS = "0123456789";
            size = 20;
        }
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    @RequestMapping(value="reservations", method = RequestMethod.GET)
    public @ResponseBody List<Reservations> getReservations(@RequestParam("userId") long userId){
        return reservationsService.getAllByUserId(userId);
    }

    @RequestMapping(value="/reservations/{id}", method = RequestMethod.GET)
    public  @ResponseBody Reservations getAReservationByUserId(@PathVariable Long id,  @RequestParam("userId") long userId){
       return reservationsService.getAReservationByUserId(id, userId);
    }
    @RequestMapping(value="/reservations/tickets/{id}", method = RequestMethod.GET)
    public  @ResponseBody List<Tickets> getAReservationByUserId(@PathVariable Long id){
        return ticketsService.getATicket(id);
    }

    @DeleteMapping(path="/reservations{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable long id){
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(id);
        if (reservation.isEmpty()){
            return ResponseEntity.badRequest().body("Reservation with given id doesn't exist");
        }
        reservationsService.deleteReservation(id);
        return ResponseEntity.ok().body("Reservation with code : " + id + " was deleted");
    }
}
    
