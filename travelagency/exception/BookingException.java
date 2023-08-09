package com.bergeskar.travelagency.exception;

public class BookingException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String objClass;
    private String reason;
    private Object instance;

    public BookingException(String objClass, String reason, Object instance){
        super(String.format("Booking with %s failed : %s : %s", objClass, reason, instance));
        this.objClass = objClass;
        this.reason = reason;
        this.instance = instance;
    }

    public String getObjClass() {
        return objClass;
    }

    public String getReason() {
        return reason;
    }

    public Object getInstance() {
        return instance;
    }
}

