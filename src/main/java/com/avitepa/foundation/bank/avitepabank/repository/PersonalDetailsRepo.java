package com.avitepa.foundation.bank.avitepabank.repository;

import com.avitepa.foundation.bank.avitepabank.entity.CustomerPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDetailsRepo extends JpaRepository<CustomerPersonalDetails,Long> {
}
