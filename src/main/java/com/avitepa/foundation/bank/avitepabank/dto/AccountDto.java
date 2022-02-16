package com.avitepa.foundation.bank.avitepabank.dto;

import com.avitepa.foundation.bank.avitepabank.entity.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AccountDto {
    private LocalDateTime lastTransaction;
    private LocalDateTime dateCreated;
    private LocalDateTime updatedDate;
    private String accountNumber;

    private long amount;
    private AccountType accountType;

}
