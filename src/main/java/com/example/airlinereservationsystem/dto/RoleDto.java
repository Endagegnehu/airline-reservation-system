package com.example.airlinereservationsystem.dto;


import com.example.airlinereservationsystem.domain.UserRole;
import lombok.Data;

@Data
public class RoleDto {

    private UserRole role;
    private String userName ;

}
