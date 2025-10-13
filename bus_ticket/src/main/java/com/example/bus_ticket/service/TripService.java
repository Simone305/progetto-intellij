package com.example.bus_ticket.service;

import com.example.bus_ticket.model.Receipt;
import com.example.bus_ticket.model.Trip;
import com.example.bus_ticket.model.User;
import com.example.bus_ticket.repository.TripRepository;
import com.example.bus_ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    // Elenca tutte le corse
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // Crea una nuova corsa
    public Trip createTrip(String origin, String destination, java.time.LocalDateTime departureTime, BigDecimal price) {
        Trip trip = new Trip(origin, destination, departureTime, price);
        return tripRepository.save(trip);
    }

    // Acquisto corsa
    public Receipt buyTrip(Integer userId, Integer tripId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Corsa non trovata"));

        if (user.getCredit().compareTo(trip.getPrice()) < 0) {
            throw new RuntimeException("Credito insufficiente");
        }

        // Scala il credito
        BigDecimal newCredit = user.getCredit().subtract(trip.getPrice());
        user.setCredit(newCredit);
        userRepository.save(user);

        // Crea il receipt
        Receipt receipt = new Receipt();
        receipt.setUserId(userId);
        receipt.setTripId(tripId);
        receipt.setCharge(trip.getPrice());
        receipt.setRemainingCredit(newCredit);

        return receipt;
    }
}
