package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.repository.RoleRepository;
import com.example.airlinereservationsystem.repository.UserRepository;
import com.example.airlinereservationsystem.util.UserSecurityDetailsImpl;
import com.example.airlinereservationsystem.util.constant.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void addUser(User user) {
//        addUserRole(user.getRole());
        userRepository.save(user);
    }

//    @Override
//    public void addUserRole(List<UserRole> roles) {
//        roleRepository.save(roles);
//    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails getUserDetails(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "+ username));
        return user.map(UserSecurityDetailsImpl::new).get();
    }
}
