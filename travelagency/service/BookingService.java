package com.bergeskar.travelagency.service;

import com.bergeskar.travelagency.VO.BookingVO;
import com.bergeskar.travelagency.exception.ResourceNotFoundException;
import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import com.bergeskar.travelagency.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findClashBookings(List<BookingDate> clashDates) {
        return bookingRepository.findByBookingDateIn(clashDates);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findBookingsByCustomerId(Customer customer) {
        return bookingRepository.findByCustomerId(customer.getId());
    }

    public void cancelBooking(Booking booking) {

        Booking b = bookingRepository.findById(booking.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", booking));

        b.setBookingDate(null);
        b.setCustomer(null);
        b.setDestination(null);

        bookingRepository.save(b);
    }

    public List<BookingVO> fromBookingsToBookingVOs(List<Booking> bookings) {

        List<BookingVO> bookingVOs = new ArrayList<>();
        bookings.forEach((b) -> {
            BookingVO bookingVO = new BookingVO();
            bookingVO.setId(b.getId());
            bookingVO.setBookingDate(b.getBookingDate());


            if (b.getDestination() != null)
                bookingVO.setDestination(b.getDestination());

            bookingVOs.add(bookingVO);
        });
        return bookingVOs;
    }

    public Booking findBookingById(long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        if (booking.isPresent())
            return booking.get();
        else
            throw new ResourceNotFoundException("Booking", "Id", bookingId);
    }

    public List<Booking> findBookingsByDestination(Destination destination) {
        return bookingRepository.findByDestination(destination);
    }
}
