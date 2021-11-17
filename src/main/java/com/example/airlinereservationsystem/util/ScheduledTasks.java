package com.example.airlinereservationsystem.util;

import com.example.airlinereservationsystem.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    EmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/5 * * * * *")
    public void reportCurrentTime() {
            emailService.sendEmail(new Email(
                    "endagegnehu@gmail.com",
                    "We what to tell you that you are awesome",
                    "About how awesome you are"));

        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @FeignClient("emailService")
    interface EmailService{
        @RequestMapping("/sendEmail")
        String sendEmail(@RequestBody Email email);
    }
}
