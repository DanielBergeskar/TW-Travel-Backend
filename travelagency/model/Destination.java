package com.bergeskar.travelagency.model;

import javax.persistence.*;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private long id;

    @Column(name = "hotel", nullable = false)
    private String hotel;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;


    @Column(name = "cost_p_day", nullable = false)
    private double cost_p_day;


    public Destination() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getCost_p_day() {
        return cost_p_day;
    }

    public void setCost_p_day(double cost_p_day) {
        this.cost_p_day = cost_p_day;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Destination))
            return false;

        Destination destination = (Destination) obj;

        return this.id == destination.getId()
                && this.hotel.equals(destination.getHotel())
                && this.country.equals(destination.getCountry())
                && this.city.equals(destination.getCity())
                && this.cost_p_day == destination.getCost_p_day();
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", hotel='" + hotel + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", cost_p_day=" + cost_p_day +
                '}';
    }
}
