package com.avitepa.foundation.bank.avitepabank.dto;

import com.avitepa.foundation.bank.avitepabank.entity.Account;
import com.avitepa.foundation.bank.avitepabank.entity.CustomerPersonalDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto {

    private long customerId;
    private LocalDateTime dateTime;
    private LocalDateTime lastLogin;
    private CustomerDetails customerPersonalDetails;
    private List<AccountDto> accountList;

}
