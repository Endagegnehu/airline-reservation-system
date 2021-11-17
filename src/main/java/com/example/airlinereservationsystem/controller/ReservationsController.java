package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.*;
import com.example.airlinereservationsystem.dto.ConfirmationDto;
import com.example.airlinereservationsystem.dto.ReservationsDto;
import com.example.airlinereservationsystem.service.interfaces.FlightInstanceService;
import com.example.airlinereservationsystem.service.interfaces.ReservationsService;
import com.example.airlinereservationsystem.service.interfaces.TicketsService;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import com.example.airlinereservationsystem.util.DataHandler;
import com.example.airlinereservationsystem.util.ResponseHandler;
import com.example.airlinereservationsystem.util.security.JwtUtil;
import com.example.airlinereservationsystem.util.security.UserAuth;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class ReservationsController {
    Logger logger = LoggerFactory.getLogger(TicketsController.class);

    @Autowired
    private ReservationsService reservationsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private FlightInstanceService flightinstanceService;


    @PostMapping("/reservations")
    public  ResponseEntity<?> addReservation(@RequestBody ReservationsDto reservationsDto){
        Reservations reservation = new Reservations();

        // get passenger user
        final Optional<User> user = userService.findUserByID(reservationsDto.getUserId());
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "));
        reservation.setUser(user.get());

        // get a performer user
        UserAuth userAuth = new UserAuth();
        User performedUser = userAuth.getUserFromAuth(userService);
        reservation.setPerformedUser(performedUser);

        long [] ids = reservationsDto.getFlightInstanceIds();
        List<FlightInstance > flightInstances = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            final Optional<FlightInstance> flightInstance = flightinstanceService.findById(ids[i]);
            flightInstance.orElseThrow(() -> new UsernameNotFoundException("No flight instance found: "));
            flightInstances.add(flightInstance.get());
        }

        reservation.setFlightInstances(flightInstances);
        Reservations response = reservationsService.addReservation(reservation);

        // return response
        if ( response != null){
            return  ResponseHandler.respond("Successfully added a reservation!", HttpStatus.OK, response);
        } else {
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reservations/confirm")
    public  ResponseEntity<?> confirmReservations(@RequestBody ConfirmationDto confirmationDto){
        // check if that reservation exists
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(confirmationDto.getReservationId());
        reservation.orElseThrow(()-> new UsernameNotFoundException("No reservation found: "));
        Reservations reservationObj = reservation.get();

        // check if the reservation performerId belongs to that user
        UserAuth userAuth = new UserAuth();
        User user = userAuth.getUserFromAuth(userService);
        if(user.getID() != reservationObj.getPerformedUser().getID())
            return  ResponseHandler.respond("Can't perform that action", HttpStatus.FORBIDDEN);

        // fetch flight instances for that reservation
        List<FlightInstance> list = reservationObj.getFlightInstances();
        List<Tickets>  ticketsResponse = new ArrayList<>();
        DataHandler handler = new DataHandler();
        for(int i = 0; i < list.size(); i++) {
            Tickets ticket = new Tickets();
            ticket.setReservation(reservationObj);
            ticket.setNumber(handler.generateRandomString("numeric"));
            ticket.setReservationCode(handler.generateRandomString("alpha"));
            ticketsResponse.add( ticketsService.addTicket(ticket));
        }

        // return response
        if ( ticketsResponse.size() != 0 ){
            return  ResponseHandler.respond("Successfully added a ticket!", HttpStatus.OK);
        } else {
            return  ResponseHandler.respond("Null entities found", HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value="reservations", method = RequestMethod.GET)
    public @ResponseBody List<Reservations> getReservations(){
        UserAuth userAuth = new UserAuth();
        User user = userAuth.getUserFromAuth(userService);
        return reservationsService.getAllByUserId(user.getID());
    }

    @RequestMapping(value="/reservations/{id}", method = RequestMethod.GET)
    public  @ResponseBody Reservations getAReservationByUserId(@PathVariable Long id){
        UserAuth userAuth = new UserAuth();
        User user = userAuth.getUserFromAuth(userService);
        return reservationsService.getAReservationByUserId(id, user.getID());
    }

    @DeleteMapping(path="/reservations/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable long id){
        final Optional<Reservations> reservation = reservationsService.findReservationsByID(id);
        if (reservation.isEmpty()){
            return ResponseEntity.badRequest().body("Reservation with given id doesn't exist");
        }
        reservationsService.deleteReservation(id);
        return ResponseEntity.ok().body("Reservation with code : " + id + " was deleted");
    }

}
    
