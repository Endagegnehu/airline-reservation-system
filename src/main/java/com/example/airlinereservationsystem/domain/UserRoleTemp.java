package com.example.airlinereservationsystem.domain;

import com.example.airlinereservationsystem.util.constant.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class UserRoleTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    String roleName;
}
