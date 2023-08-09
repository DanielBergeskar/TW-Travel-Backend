package com.bergeskar.travelagency.controller;

import com.bergeskar.travelagency.VO.BookingVO;
import com.bergeskar.travelagency.VO.ExchangeVO;
import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import com.bergeskar.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v2")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/trips")

    public ResponseEntity<List<Destination>> availableDestinations(@RequestBody BookingDate bookingDate){
        return new ResponseEntity<>(userService.findAvailableDestinations(bookingDate), HttpStatus.OK);
    }

    @PostMapping("/booktrip")
    public ResponseEntity<Booking> bookTrip(@RequestBody Booking booking){
        return new ResponseEntity<>(userService.bookDestination(booking), HttpStatus.CREATED);
    }

    @PutMapping("/updatetrip")
    public ResponseEntity<Booking> updateTrip(@RequestBody Booking booking){
        return new ResponseEntity<>(userService.updateBooking(booking), HttpStatus.OK);
    }

    @PostMapping("/mytrips")
    public ResponseEntity<List<BookingVO>> getBookings(@RequestBody Customer customer){
        return new ResponseEntity<>(userService.findCustomerBookings(customer), HttpStatus.OK);
    }

    @PostMapping("/exchange")
    public ResponseEntity<ExchangeVO> exchange(@RequestBody Booking booking){
        return new ResponseEntity<>(userService.exchange(booking), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> findCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(userService.findCustomerByUsername(customer), HttpStatus.OK);
    }
    @PostMapping("/destinations/{country}")
    public ResponseEntity<List<Destination>> findDestinationsByCountry(@PathVariable("country") String destinationCountry){
        return new ResponseEntity<>(userService.findDestinationsByCountry(destinationCountry), HttpStatus.OK);

    }
}