package com.services.investmentservices.repo;

import com.services.investmentservices.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InverstmentRepo extends JpaRepository<Investment, Integer> {
}
