package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.domain.UserRole;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.repository.UserRepository;
import com.example.airlinereservationsystem.util.UserSecurityDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return ((List<User>) userRepository
                .findAll())
                .stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());

    }

    @Override
    public void signup(User user) {
        userRepository.save(user);
    }
    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {
        List<User> users = userRepository.login(userLoginDto.getUsername(), userLoginDto.getPassword());
        if (users.isEmpty())
            return false;
        return true;
    }

    @Override
    public UserDto addRole(RoleDto role) {
        User user = userRepository.findByUsername(role.getUserName()).get();
        user.getRole().add(role.getRole());
        userRepository.save(user);
        return convertUserToUserDto(user);
    }

    @Override
    public UserDto removeRole(RoleDto role) {
        User user = userRepository.findByUsername(role.getUserName()).get();
        user.getRole().remove(role.getRole());
        userRepository.save(user);
        return convertUserToUserDto(user);
    }

    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserID(user.getID());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUserRole(user.getRole());
        return userDto;
    }
    @Override
    public UserDetails getUserDetails(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()-> new UsernameNotFoundException("No user found: "+ username));
        return user.map(UserSecurityDetailsImpl::new).get();
    }
}
