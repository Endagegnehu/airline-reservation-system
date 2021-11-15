package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.domain.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long userID;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRole> userRole;
    private Address residenceAddress;
}