package com.bergeskar.travelagency.service;

import com.bergeskar.travelagency.exception.BookingException;
import com.bergeskar.travelagency.exception.ResourceNotFoundException;

import com.bergeskar.travelagency.model.Destination;
import com.bergeskar.travelagency.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> findAllDestinations() {
        return destinationRepository.findAll();
    }

    public List<Destination> findDestinationsNotInList(List<Long> destinationIds) {
        return destinationRepository.findAllByIdNotIn(destinationIds);
    }

    public Destination findDestinationById(Destination destination) {

        Optional<Destination> d = destinationRepository.findById(destination.getId());

        if (d.isPresent())
            return d.get();
        else
            throw new ResourceNotFoundException("Destination", "Id", destination.getId());
    }
    public Destination findDestinationById(long destinationId) {

        Optional<Destination> destination = destinationRepository.findById(destinationId);

        if (destination.isPresent())
            return destination.get();
        else
            throw new ResourceNotFoundException("Destination", "Id", destinationId);
    }
    public void checkAvailability(Destination destination, List<Destination> availableDestinations){
        if (!availableDestinations.contains(destination))
            throw new BookingException("Destination", "Unavailable", destination);
    }

    public Destination addDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public void deleteDestination(Destination destination) {
        destinationRepository.deleteById(destination.getId());
    }

    public Destination updateDestination(Destination newDestination) {
        Destination oldDestination = destinationRepository.findById(newDestination.getId()).orElseThrow(() -> new ResourceNotFoundException("Destination", "Id", newDestination.getId()));

        oldDestination.setHotel(newDestination.getHotel());
        oldDestination.setCost_p_day(newDestination.getCost_p_day());


        return destinationRepository.save(oldDestination);
    }
    public List<Destination> findDestinationsByCountry(String destinationCountry) {
        return destinationRepository.findAllByCountry(destinationCountry);
    }

}

