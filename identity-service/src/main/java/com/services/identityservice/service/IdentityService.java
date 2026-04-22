package com.services.identityservice.service;

import com.services.identityservice.entity.User;
import com.services.identityservice.repo.IdentityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//handel business logic
public class IdentityService {

    @Autowired
    IdentityRepo repo;

    public User saveUser(User user) {
        User savedUser = repo.save(user);
        return savedUser;
    }

}
