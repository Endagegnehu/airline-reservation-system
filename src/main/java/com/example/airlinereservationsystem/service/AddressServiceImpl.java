package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Address getAddressById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }

    private String street;
    private String city;
    private String zipCode;
    private String state;

    @Override
    public Address updateAddress(Address updateAddress, long id) {
        Address address = addressRepository.findById(id).orElse(null);
        address.setStreet(updateAddress.getStreet());
        address.setZipCode(updateAddress.getZipCode());
        address.setCity(updateAddress.getCity());
        address.setState(updateAddress.getState());
        return addressRepository.save(address);
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }
}
