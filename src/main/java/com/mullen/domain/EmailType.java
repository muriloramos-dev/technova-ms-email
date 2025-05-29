package com.mullen.domain;

public enum EmailType {
    CONFIRMATION("confirmation"),
    RESET_PASSWORD("reset_password"),
    WELCOME("welcome"),
    NOTIFICATION("notification");

    private final String type;

    EmailType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
