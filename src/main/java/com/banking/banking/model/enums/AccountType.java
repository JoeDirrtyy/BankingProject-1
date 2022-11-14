package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {

@JsonProperty("savings")
    SAVINGS("Savings"),
    @JsonProperty("checking")
    CHECKING("Checking"),
    @JsonProperty("credit")
    CREDIT("Credit");

    private String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
