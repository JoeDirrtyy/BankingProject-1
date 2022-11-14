package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransferType {
    @JsonProperty("p2p")
    P2P("P2p"),
    @JsonProperty("deposit")
    DEPOSIT("Deposit"),
    @JsonProperty("withdrawal")
    WITHDRAWAL("Withdrawal");

    private String transferType;

    TransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
}
