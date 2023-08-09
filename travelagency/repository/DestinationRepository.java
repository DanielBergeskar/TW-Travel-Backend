package com.bergeskar.travelagency.repository;

import com.bergeskar.travelagency.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

  List<Destination> findAllByIdNotIn(List<Long>destinationIds);
  List<Destination> findAllByCountry(String country);
    }


