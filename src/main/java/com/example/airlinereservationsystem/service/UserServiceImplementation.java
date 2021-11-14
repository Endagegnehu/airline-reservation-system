package com.example.airlinereservationsystem.service;

import com.example.airlinereservationsystem.domain.User;
import com.example.airlinereservationsystem.dto.RoleDto;
import com.example.airlinereservationsystem.dto.UserLoginDto;
import com.example.airlinereservationsystem.dto.UserDto;
import com.example.airlinereservationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public boolean login(UserLoginDto userLoginDto) {
        List<User> users = userRepository.login(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (users.isEmpty())
            return false;
        return true;
    }

    @Override
    public UserDto addRole(RoleDto role) {
        User user = userRepository.findById(role.getId()).get();
        System.out.println("user: " + role  + "  " +  role.getRole());
        user.getUserRoles().add(role.getRole());
        userRepository.save(user);
        return convertUserToUserDto(user);
    }

    @Override
    public UserDto removeRole(RoleDto role) {
        User user = userRepository.findById(role.getId()).get();
        user.getUserRoles().remove(role.getRole());
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
        userDto.setUserRole(user.getUserRoles());
        return userDto;
    }
}
