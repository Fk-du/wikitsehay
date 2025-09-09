package com.bank.tsehay.wikitsehay.exceptions;

public class FieldAlreadyExistsException extends RuntimeException {
    private final String field;

    public FieldAlreadyExistsException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
