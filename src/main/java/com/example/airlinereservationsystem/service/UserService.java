package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public interface UserService {
     void addUser(User user);
     Optional<User> findUserByUsername(String firstName);
     UserDetails getUserDetails(String firstName);
     Optional<User> findUserByID(long id);

}
