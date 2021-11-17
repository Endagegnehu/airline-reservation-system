package com.example.airlinereservationsystem.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {
    private String sendTo;
    private String emailBody;
    private String emailSubject;

}

