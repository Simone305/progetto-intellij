package com.example.bus_ticket.controller;

import com.example.bus_ticket.model.Receipt;
import com.example.bus_ticket.model.Trip;
import com.example.bus_ticket.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // Elenco tutte le corse
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    // Crea una nuova corsa
    @PostMapping
    public Trip createTrip(@RequestParam String origin,
                           @RequestParam String destination,
                           @RequestParam String departureTime, // in formato ISO string
                           @RequestParam BigDecimal price) {
        LocalDateTime dt = LocalDateTime.parse(departureTime);
        return tripService.createTrip(origin, destination, dt, price);
    }

    // Acquisto corsa
    @PostMapping("/{tripId}/buy")
    public Receipt buyTrip(@RequestParam Integer userId,
                           @PathVariable Integer tripId) {
        return tripService.buyTrip(userId, tripId);
    }
}

