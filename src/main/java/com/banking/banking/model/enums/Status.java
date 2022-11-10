package com.banking.banking.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("pending")
    PENDING("Pending"),
    @JsonProperty("cancelled")
    CANCELLED("Cancelled"),
    @JsonProperty("completed")
    COMPLETED("Completed"),
    @JsonProperty("recurring")
    RECURRING("Recurring");

   private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
