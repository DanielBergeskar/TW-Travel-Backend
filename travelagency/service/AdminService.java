package com.bergeskar.travelagency.service;

import com.bergeskar.travelagency.VO.CustomerVO;
import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private BookingService bookingService;

    public List<CustomerVO> findAllCustomers() {

        List<Customer> customers = customerService.findAvailableDestinations();

        List<CustomerVO> customerVOs = new ArrayList<>();


        customers.forEach((customer) -> {

            CustomerVO customerVO = new CustomerVO();
            customerVO.setCustomerId(customer.getId());
            customerVO.setUsername(customer.getUsername());
            customerVO.setName(customer.getName());
            customerVO.setAddress(customer.getAddress());
            customerVO.setBookings(new ArrayList<>());

            //Hämta alla ordrar som tillhör customer
            List<Booking> bookings = bookingService.findBookingsByCustomerId(customer);
            if (!bookings.isEmpty()) {
                //Gör om alla hittade orders till orderVO obj
                bookingService.fromBookingsToBookingVOs(bookings).forEach((bookingVO) -> customerVO.getBookings().add(bookingVO));
            }
            customerVOs.add(customerVO);
        });

        return customerVOs;
    }

    public Destination addDestination(Destination destination) {
        return destinationService.addDestination(destination);
    }

    public Customer addCustomer(Customer customer) {

        return customerService.addCustomer(customer);
    }

    public void deleteDestination(Destination destination) {

        destinationService.deleteDestination(destination);
    }

    public Destination updateDestination(Destination destination) {
        return destinationService.updateDestination(destination);
    }

    public void cancelBooking(Booking booking) {

        bookingService.cancelBooking(booking);
    }

    public List<Destination> findAllDestinations() {

        return destinationService.findAllDestinations();
    }

    public List<Booking> bookingsByDestination(Destination destination) {
        return bookingService.findBookingsByDestination(destination);
    }

    public Destination findDestinationById(long destinationId) {
        return destinationService.findDestinationById(destinationId);

    }
}
