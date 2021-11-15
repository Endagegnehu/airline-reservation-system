package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressService extends JpaRepository<Address,Long> {
    Address getAddresById(String ID);
}
