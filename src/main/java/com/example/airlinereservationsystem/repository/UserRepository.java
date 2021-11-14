package com.example.airlinereservationsystem.repository;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u from User u where  u.email = :email  and u.password = :password")
    public List<User> login(@Param("email") String email,
                            @Param("password") String password);
}
