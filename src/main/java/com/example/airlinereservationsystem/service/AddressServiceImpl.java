package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address getAddress(Long ID) {
        return addressRepository.findById(ID).get();
    }
}
