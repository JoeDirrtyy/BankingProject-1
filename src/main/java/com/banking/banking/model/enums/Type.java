package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {

@JsonProperty("savings")
    SAVINGS,
    @JsonProperty("checking")
    CHECKING,
    @JsonProperty("credit")
    CREDIT

}
