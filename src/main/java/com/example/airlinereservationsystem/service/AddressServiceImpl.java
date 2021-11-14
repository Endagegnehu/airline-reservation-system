package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService{
    @Override
    public Address getAddressById(String ID) {
        return new Address(null,"street","city","state","zipcode");
    }
}
