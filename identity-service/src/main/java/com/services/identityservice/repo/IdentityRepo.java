package com.services.identityservice.repo;

import com.services.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//handel the DB logic
public interface IdentityRepo extends JpaRepository<User,Integer> {
    User findByName(String userName);
}
