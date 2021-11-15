package com.example.airlinereservationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String street;
    private String city;
    private String zipCode;
    private String state;

}
