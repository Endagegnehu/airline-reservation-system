package com.example.airlinereservationsystem.util;

import com.example.airlinereservationsystem.domain.Email;
import com.example.airlinereservationsystem.domain.Flight;
import com.example.airlinereservationsystem.domain.FlightInstance;
import com.example.airlinereservationsystem.domain.Reservations;
import com.example.airlinereservationsystem.service.interfaces.ReservationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Transactional
public class ScheduledTasks {

    @Autowired
    EmailService emailService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    ReservationsService reservationsService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${app.cronJob.time}")
    public void reportCurrentTime() {
        LocalDate localDate = LocalDate.now();
        List<Reservations> reservationsList = reservationsService.getAllReservation();
        for(Reservations reservations: reservationsList) {
                for(FlightInstance flightInstance : reservations.getFlightInstances()){
                    if(flightInstance.getDepartureDate().getDayOfMonth() - 1 == localDate.getDayOfMonth() ){
                       String userEmail =  reservations.getUser().getEmail();
                       try {
                           jmsTemplate.convertAndSend("emailQueue",userEmail);
                       }catch (Exception exception){
                           log.error(exception.getMessage());
                       }
                    }
                }
        }
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @FeignClient("emailService")
    interface EmailService{
        @RequestMapping("/sendEmail")
        String sendEmail(@RequestBody Email email);
    }

    @JmsListener(destination = "emailQueue")
    public void messageListener(String  userEmail) {
        log.info("Email sent to", userEmail);
        emailService.sendEmail(new Email(
                userEmail,
                "You have a flight after 24 hrs",
                "Ticket Reservation Reminder"));
    }
}
