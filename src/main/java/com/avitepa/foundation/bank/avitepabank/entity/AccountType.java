package com.avitepa.foundation.bank.avitepabank.entity;

public enum AccountType {

    SAVINGS("SAVINGS"),
    CREDIT("CREDIT"),
    LOAN("LOAN");

    AccountType(String code) {
        this.code=code;
    }

    private  String code;

    public String code() {
        return this.code;
    }

}

