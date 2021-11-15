package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;

public interface AddressService extends JpaRepository<Address,Long> {
    Address getAddresById(String ID);
}
