package com.avitepa.foundation.bank.avitepabank.dto;

import java.util.ArrayList;

public class CustomerRequest {
    private ArrayList<Long> customerIdsInput;

    public ArrayList<Long> getCustomerIdsInput() {
        return customerIdsInput;
    }

    public void setCustomerIdsInput(ArrayList<Long> customerIdsInput) {
        this.customerIdsInput = customerIdsInput;
    }
}
