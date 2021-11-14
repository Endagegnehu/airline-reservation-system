package com.example.airlinereservationsystem.repository;


import com.example.airlinereservationsystem.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
