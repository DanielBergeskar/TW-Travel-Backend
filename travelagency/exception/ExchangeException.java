package com.bergeskar.travelagency.exception;

public class ExchangeException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String name;
    private String field;
    private Object value;

    public ExchangeException(String name, String field, Object value) {
        super(String.format("Could not exchange %s with %s : '%s'", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}

