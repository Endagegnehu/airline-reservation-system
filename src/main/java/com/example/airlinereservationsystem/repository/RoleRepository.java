package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Integer> {
}
