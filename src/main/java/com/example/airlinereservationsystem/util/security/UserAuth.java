package com.example.airlinereservationsystem.util.security;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.repository.UserRepository;
import com.example.airlinereservationsystem.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserAuth
{

    public User getUserFromAuth(UserService userService){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        final Optional<User> user = userService.findUserByUsername(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "));
        return user.get();
    }
}
