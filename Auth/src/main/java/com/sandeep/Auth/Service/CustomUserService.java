package com.sandeep.Auth.Service;

import com.sandeep.Auth.Model.Users;
import com.sandeep.Auth.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> users=userRepository.findByUserEmail(username);
        Users users1=userRepository.findByUserEmail(username).orElseThrow(()->new UsernameNotFoundException("user with email "+username+" not found"));
        return  new org.springframework.security.core.userdetails.User(users1.getUserEmail(),users1.getPassword(),new ArrayList<>());

    }
}
