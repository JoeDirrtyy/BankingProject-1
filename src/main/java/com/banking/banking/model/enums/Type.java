package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {

@JsonProperty("savings")
    SAVINGS,
    @JsonProperty("checking")
    CHECKING,
    @JsonProperty("credit")
    CREDIT,
    @JsonProperty("p2p")
    P2P("P2p"),
    @JsonProperty("deposit")
    DEPOSIT("Deposit"),
    @JsonProperty("withdrawal")
    WITHDRAWAL("Withdrawal");

    private String type;

    Type() {
    }

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    //WORK BITCH
}
