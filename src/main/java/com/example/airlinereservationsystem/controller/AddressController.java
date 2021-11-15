package com.example.airlinereservationsystem.controller;


import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    List<Address> getAllAddresses(){
        System.out.println("inside getAllAddresses controller.");
        return addressService.getAllAddresses();
    }

    @GetMapping("/addresses/{id}")
    Address getAddress(@PathVariable long id){
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/admin/addresses/{id}")
    void deleteAddress(@PathVariable long id){
        addressService.deleteAddress(id);
    }

    @PutMapping("/admin/addresses/{id}")
    void updateAddress(@RequestBody  Address address, @PathVariable  long id){
        addressService.updateAddress(address, id);
    }

    @PostMapping("/admin/addresses")
    void updateAddress(@RequestBody Address address){
        addressService.addAddress(address);
    }
}
