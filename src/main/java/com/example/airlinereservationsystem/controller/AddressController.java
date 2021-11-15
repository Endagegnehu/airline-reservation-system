package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;



    @GetMapping("/addresses/{id}")
    Address getAddress(@PathVariable long id){
        return addressService.getAddress(id);
    }


}
