package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Medium {
    //  used to map property names with JSON keys

    @JsonProperty("balance")
    BALANCE("Balance"),
    @JsonProperty("rewards")
    REWARDS("Rewards");

    private String medium;

    Medium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
