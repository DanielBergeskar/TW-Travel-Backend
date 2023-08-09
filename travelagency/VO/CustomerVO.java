package com.bergeskar.travelagency.VO;

import java.util.List;

public class CustomerVO {

    private long customerId;
    private String username;
    private String name;
    private String address;
    private List<BookingVO> bookings;

    public CustomerVO() {
    }

    public CustomerVO(long customerId, String username, String name, String address, List<BookingVO> bookings) {
        this.customerId = customerId;
        this.username = username;
        this.name = name;
        this.address = address;
        this.bookings = bookings;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<BookingVO> getBookings() {
        return bookings;
    }
    public void setBookings(List<BookingVO> bookings) {
        this.bookings = bookings;
    }
}
