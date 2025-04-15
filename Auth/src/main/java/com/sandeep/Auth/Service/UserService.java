package com.sandeep.Auth.Service;


import com.sandeep.Auth.DTO.UserDto;
import com.sandeep.Auth.Model.Users;
import com.sandeep.Auth.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    public PasswordEncoder encoder;

    public Users registerUser(UserDto userDto){
        Users users=new Users();
        users.setUserEmail(userDto.getEmail());
        users.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(users);
    }

    public Optional<Users> findByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    public Users findByUsername(String userEmail) {
        Optional<Users> users=userRepository.findByUserEmail(userEmail);
        return users.orElse(null);

    }
}
