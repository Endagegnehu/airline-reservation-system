package com.example.airlinereservationsystem.dto;

import com.example.airlinereservationsystem.util.constant.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Roles roleName;
}
