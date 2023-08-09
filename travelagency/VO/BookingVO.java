package com.bergeskar.travelagency.VO;

import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Destination;

public class BookingVO {

    private long id;
    private BookingDate bookingDate;
    private Destination destination;

    public BookingVO() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
}
