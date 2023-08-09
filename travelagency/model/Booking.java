package com.bergeskar.travelagency.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_date_id_fk", referencedColumnName = "booking_date_id")
    private BookingDate bookingDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "destination_id_fk", referencedColumnName = "destination_id")
    private Destination destination;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
    private Customer customer;

    public Booking() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Booking))
            return false;

        Booking booking = (Booking) obj;

        return this.id == booking.getId()
                && this.bookingDate.equals(booking.getBookingDate())
                && this.customer.equals(booking.getCustomer())
                && this.destination.equals(booking.getDestination());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", destination=" + destination +
                ", customer=" + customer +
                '}';
    }


}