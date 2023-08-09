package com.bergeskar.travelagency.VO;

import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Destination;

public class ExchangeVO {
    private BookingDate bookingDate;
    private Destination destination;
    private String from;
    private String to;
    private double amount;
    private double rate;
    private double result;

    public ExchangeVO() {
    }

    public BookingDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(BookingDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}


