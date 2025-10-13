package com.example.bus_ticket.repository;


import com.example.bus_ticket.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    // puoi aggiungere query custom se servono filtri
}
