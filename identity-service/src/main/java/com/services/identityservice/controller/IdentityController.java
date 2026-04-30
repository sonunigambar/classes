package com.services.identityservice.controller;

import com.services.identityservice.entity.AuthRequest;
import com.services.identityservice.entity.User;
import com.services.identityservice.service.IdentityService;
import com.services.identityservice.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class IdentityController {

    @Autowired
    IdentityService identityService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtToken jwtToken;

    @PostMapping("/authenticate")
    public String auth(@RequestBody AuthRequest authRequest) {
        String token = null;
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            token = jwtToken.generateToken(authRequest.getUserName(), authenticate.getAuthorities());
        }


        return token;
    }
    @GetMapping("/getMsg")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMsg() {
        return "Hello World";
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return identityService.saveUser(user);
    }

}
