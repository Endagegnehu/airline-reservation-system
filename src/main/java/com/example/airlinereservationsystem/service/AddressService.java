package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;

import java.util.List;

public interface AddressService {
    public List<Address> getAllAddresses();
    public Address getAddress(long id);
    public void deleteAddress(long id);
    public Address updateAddress(Address address, long id);
    public void addAddress(Address address);

}
