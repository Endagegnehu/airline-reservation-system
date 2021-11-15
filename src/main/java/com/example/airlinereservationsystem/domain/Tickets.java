package com.example.airlinereservationsystem.domain;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private BigInteger number;
    private String reservationCode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id", referencedColumnName = "ID")
    private Reservations reservation;
}
