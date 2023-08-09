package com.bergeskar.travelagency.service;

import com.bergeskar.travelagency.exception.DateException;
import com.bergeskar.travelagency.exception.ResourceNotFoundException;
import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.repository.BookingDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDateService {
    @Autowired
    private BookingDateRepository bookingDateRepository;

    public List<BookingDate> findClashDates(BookingDate bookingDate) {
        LocalDate start = bookingDate.getStartDate();
        LocalDate end = bookingDate.getEndDate();
        return bookingDateRepository.findByStartDateBetweenOrEndDateBetween(start,end, start,end);
    }

    public BookingDate findBookingDateById(BookingDate bookingDate){
        Optional<BookingDate> bd = bookingDateRepository.findById(bookingDate.getId());

        if (bd.isPresent())
            return bd.get();
        else
            throw new ResourceNotFoundException("BookingDate", "Id", bookingDate.getId());
    }

    public void validateDates(BookingDate bookingDate) {
        if (bookingDate.getStartDate().isAfter(bookingDate.getEndDate()))
            throw new DateException(bookingDate.getStartDate(), bookingDate.getEndDate(), bookingDate.getClass());
    }
}
