package com.avitepa.foundation.bank.avitepabank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CustomerDetails {

    private String firstName;
    private LocalDateTime dateOfBirth;
    private String phone;
    private String mobile;
    private String maritalStatus;
    private String gender;

}
