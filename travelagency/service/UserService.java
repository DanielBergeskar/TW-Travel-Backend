package com.bergeskar.travelagency.service;

import com.bergeskar.travelagency.VO.BookingVO;
import com.bergeskar.travelagency.VO.ExchangeVO;
import com.bergeskar.travelagency.exception.ExchangeException;
import com.bergeskar.travelagency.model.Booking;
import com.bergeskar.travelagency.model.BookingDate;
import com.bergeskar.travelagency.model.Customer;
import com.bergeskar.travelagency.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private BookingDateService bookingDateService;

    @Autowired
    private RestTemplate restTemplate;

    public List<Destination> findAvailableDestinations(BookingDate bookingDate) {
        //Hitta alla BookingDate som har start eller slutdatum mellan angivna datum
        List<BookingDate> clashDates = bookingDateService.findClashDates(bookingDate);
        if (clashDates.isEmpty())
            return destinationService.findAllDestinations(); //Om ingen bokning finns mellan angivna datum

        //Hämta alla bokningar som tillhör någon BookingDate i clashDates
        List<Booking> clashBookings = bookingService.findClashBookings(clashDates);

        //Plocka ut alla bokade resors IDs
        List<Long> destinationIds = new ArrayList<>();
        clashBookings.forEach((b) -> destinationIds.add(b.getDestination().getId()));

        return destinationService.findDestinationsNotInList(destinationIds);
    }

    public Booking bookDestination(Booking booking) {
        //Kolla så att datumen är korrekt angivna
       System.out.println(booking.getBookingDate());
        bookingDateService.validateDates(booking.getBookingDate());

        //Finns kunden och resan i db?
        Customer newCustomer = customerService.findCustomerById(booking.getCustomer());
        Destination newDestination = destinationService.findDestinationById(booking.getDestination());

        //Är resan bokningsbar?
        List<Destination> availableDestinations = findAvailableDestinations(booking.getBookingDate());
        destinationService.checkAvailability(newDestination, availableDestinations);

        booking.setCustomer(newCustomer);
        booking.setDestination(newDestination);

        return bookingService.saveBooking(booking);
    }

    public Booking updateBooking(Booking updBooking) {
        //Kollar så att de nya datumen är korrekt angivna
        bookingDateService.validateDates(updBooking.getBookingDate());

        //Kollar så kund och resa finns i db
        Customer updCustomer = customerService.findCustomerById(updBooking.getCustomer());
        Destination updDestination = destinationService.findDestinationById(updBooking.getDestination());

        Booking oldBooking = bookingService.findBookingById(updBooking.getId());
        if (!oldBooking.getDestination().equals(updDestination)){
            //Kontrolera så att resan är ledig
            List<Destination> availableDestinations = findAvailableDestinations(updBooking.getBookingDate());
            destinationService.checkAvailability(updDestination, availableDestinations);
        }

        //Hämta bokningens gamla värden
        Booking booking = bookingService.findBookingById(updBooking.getId());

        //Nya värden
        booking.getBookingDate().setStartDate(updBooking.getBookingDate().getStartDate());
        booking.getBookingDate().setEndDate(updBooking.getBookingDate().getEndDate());
        booking.setCustomer(updCustomer);
        booking.setDestination(updDestination);

        return bookingService.saveBooking(booking);
    }

    public List<BookingVO> findCustomerBookings(Customer customer) {
        //Hämta alla ordrar för kunden
        List<Booking> bookings = bookingService.findBookingsByCustomerId(customer);


        return bookingService.fromBookingsToBookingVOs(bookings);
    }

    public ExchangeVO exchange(Booking booking) {
        //Hämta ordern
        Booking fullBooking = bookingService.findBookingById(booking.getId());

        //Antal dagar i bokningen
        int days = 1 + Period.between(fullBooking.getBookingDate().getStartDate(), fullBooking.getBookingDate().getEndDate()).getDays();

        //Räkna ut totala priset i SEK
        double totalPrice =Math.round((fullBooking.getDestination().getCost_p_day() * days) * 100.0) / 100.0 ;

        //Gå via uppsatt gateway för att kalla på ett publikt API för valutaväxlingen
        ExchangeVO exchangeVO = restTemplate.getForObject("http://EXCHANGE-SERVICE/exchange/"+totalPrice, ExchangeVO.class);

        //Lägg till värden från Booking till ExchangeVO
        if (exchangeVO != null) {
            exchangeVO.setBookingDate(fullBooking.getBookingDate());
            exchangeVO.setDestination(fullBooking.getDestination());
            return exchangeVO;
        } else {
            throw new ExchangeException("Booking", "Id", fullBooking.getId());
        }
    }

    public Customer findCustomerByUsername(Customer customer) {
        return customerService.findCustomerByUsername(customer);
    }
    public List<Destination> findDestinationsByCountry(String destinationCountry) {
        return (destinationCountry.equals("All") ? destinationService.findAllDestinations() : destinationService.findDestinationsByCountry(destinationCountry));
    }

}
