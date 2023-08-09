package com.bergeskar.travelagency.repository;

import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDateIn(List<BookingDate> bookingDate);

    List<Booking> findByCustomerId(long customerId);

    List<Booking> findByDestination(Destination destination);
}

