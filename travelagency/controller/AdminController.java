package com.bergeskar.travelagency.controller;

import com.bergeskar.travelagency.VO.CustomerVO;
import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import com.bergeskar.travelagency.repository.CustomerRepository;
import com.bergeskar.travelagency.repository.DestinationRepository;
import com.bergeskar.travelagency.service.AdminService;
import com.bergeskar.travelagency.service.BookingService;
import com.bergeskar.travelagency.service.CustomerService;
import com.bergeskar.travelagency.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/customers")
    public ResponseEntity<List<CustomerVO>> allCustomers(){
        return new ResponseEntity<>(adminService.findAllCustomers(), HttpStatus.OK);
    }

    @PostMapping("/adddestination")
    public ResponseEntity<Destination> addDestination(@RequestBody Destination destination){
        return new ResponseEntity<>(adminService.addDestination(destination), HttpStatus.CREATED);
    }
    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(adminService.addCustomer(customer), HttpStatus.CREATED);
    }
    @DeleteMapping("/deletedestination")
    public void deleteDestination(@RequestBody Destination destination){
        adminService.deleteDestination(destination);
    }

    @PutMapping("/updatedestination")
    public ResponseEntity<Destination> updateDestination(@RequestBody Destination destination){
        return new ResponseEntity<>(adminService.updateDestination(destination), HttpStatus.OK);
    }

    @PutMapping("/cancelbooking")
    public void cancelBooking(@RequestBody Booking booking){
        adminService.cancelBooking(booking);
    }

    @PostMapping("/alldestinations")
    public ResponseEntity<List<Destination>> allDestinations(){
        return new ResponseEntity<>(adminService.findAllDestinations(), HttpStatus.OK);
    }

    @PostMapping("/bookingsbydestination")
    public ResponseEntity<List<Booking>> bookingsByDestinationId(@RequestBody Destination destination){
        return new ResponseEntity<>(adminService.bookingsByDestination(destination), HttpStatus.OK);
    }
    @PostMapping("/destination/{id}")
    public ResponseEntity<Destination> allCustomers(@PathVariable("id") long destinationId){
        return new ResponseEntity<>(adminService.findDestinationById(destinationId), HttpStatus.OK);
    }

}



