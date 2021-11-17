package com.example.airlinereservationsystem.util;

import com.example.airlinereservationsystem.controller.TicketsController;
import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.domain.Tickets;
import com.example.airlinereservationsystem.dto.ModifiedTicket;
import com.example.airlinereservationsystem.dto.TicketsResponseDto;
import com.example.airlinereservationsystem.util.exception.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class ResponseHandler {
    public static ResponseEntity<Object> respond(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<?> respond(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<?> respond(ErrorDetails errorDetails, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", errorDetails.getMessage());
        map.put("status", status.value());

        return new ResponseEntity<>(map,status);
    }

    public static TicketsResponseDto modifyTickets(List<Tickets> tickets){
        TicketsResponseDto response = new TicketsResponseDto();

        for(int x = 0; x < tickets.size(); x++) {
            ModifiedTicket modifiedTicket = new ModifiedTicket();

            Reservations res = tickets.get(x).getReservation();

            modifiedTicket.setCode(tickets.get(x).getReservationCode());
            modifiedTicket.setNumber(tickets.get(x).getNumber());
            modifiedTicket.setId(tickets.get(x).getID());
            modifiedTicket.setFlightInstance(res.getFlightInstances().get(x));

            response.setUser(res.getUser());
            response.setReservationId(res.getID());
            response.setTickets(modifiedTicket);
            log.info("value of tickets {}", tickets.get(x));
        }

        return response;

    }
}