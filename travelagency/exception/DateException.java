package com.bergeskar.travelagency.exception;

import java.time.LocalDate;

public class DateException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private LocalDate startDate;
    private LocalDate endDate;
    private Object objClass;

    public DateException(LocalDate startDate, LocalDate endDate, Object objClass) {
        super(String.format("Given starting date '%s' comes after given end date '%s' : '%s'", startDate, endDate, objClass));
        this.startDate = startDate;
        this.endDate = endDate;
        this.objClass = objClass;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Object getObjClass() {
        return objClass;
    }


}
