package com.example.airlinereservationsystem.service.interfaces;

import com.example.airlinereservationsystem.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public List<Address> getAllAddresses();
    public Address getAddressById(long id);
    public void deleteAddress(long id);
    public Address updateAddress(Address address, long id);
    public void addAddress(Address address);

}
