package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.Address;
import com.example.airlinereservationsystem.repository.AddressRepository;
import com.example.airlinereservationsystem.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Address getAddressById(long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(!address.isPresent()){
            throw new IllegalStateException("Address with given ID does not exist (Id: " + id);
        }

        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAddress(long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(!address.isPresent()){
            throw new IllegalStateException("Address with given ID does not exist (Id: " + id);
        }
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address updateAddress, long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(!address.isPresent()){
                throw new IllegalStateException("Address with given ID does not exist (Id: " + updateAddress.getId()+")");
            }
        address.get().setStreet(updateAddress.getStreet());
        address.get().setZipCode(updateAddress.getZipCode());
        address.get().setCity(updateAddress.getCity());
        address.get().setState(updateAddress.getState());
        return addressRepository.save(address.get());
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }
}
