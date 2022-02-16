package com.avitepa.foundation.bank.avitepabank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private Long amount;
    private String sourceAccountId;
    private String targetAccountId;

}
