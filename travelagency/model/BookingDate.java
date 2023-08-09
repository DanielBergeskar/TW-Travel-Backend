package com.bergeskar.travelagency.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "booking_date")
public class BookingDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_date_id")
    private long id;
    @Column(name = "departure")
    private LocalDate startDate;
    @Column(name = "home")
    private LocalDate endDate;

    public BookingDate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof BookingDate))
            return false;

        BookingDate date = (BookingDate) obj;

        return this.id == date.getId()
                && this.startDate.equals(date.getStartDate())
                && this.endDate.equals(date.getEndDate());
    }


    @Override
    public String toString() {
        return "BookingDate{" +
                "id=" + id +
                ", startdate=" + startDate +
                ", enddate=" + endDate +
                '}';
    }
}
