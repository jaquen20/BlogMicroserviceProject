package com.sandeep.Auth.Controller;


import com.sandeep.Auth.DTO.UserDto;
import com.sandeep.Auth.JwtConfig.JwtHelper;
import com.sandeep.Auth.Model.Users;
import com.sandeep.Auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController @RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
        public ResponseEntity<?> Login(@RequestBody UserDto userAuth){
        try {
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userAuth.getEmail(),userAuth.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Users users=userService.findByUsername(authentication.getName());

            final String token= jwtHelper.generateToken(users.getUserEmail());
            return ResponseEntity.ok("token " + token);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        try{
            if (userService.findByEmail(userDto.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("email already exists");
            }
            userService.registerUser(userDto);
            Map<String, String> response=new HashMap<>();
            response.put("message","signup successful");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("signup failed");
        }
    }
}
