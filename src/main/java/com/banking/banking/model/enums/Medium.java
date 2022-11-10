package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Medium {
    @JsonProperty("alance")
    BALANCE("Balance"),
    @JsonProperty("Rewards")
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
