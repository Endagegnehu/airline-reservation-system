package com.example.airlinereservationsystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Email {
    private String sendTo;
    private String emailBody;
    private String emailSubject;

}