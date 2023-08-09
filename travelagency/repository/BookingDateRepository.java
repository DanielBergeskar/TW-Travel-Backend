package com.bergeskar.travelagency.repository;

import com.bergeskar.travelagency.model.BookingDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {
    List<BookingDate>findByStartDateBetweenOrEndDateBetween(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2);

}
