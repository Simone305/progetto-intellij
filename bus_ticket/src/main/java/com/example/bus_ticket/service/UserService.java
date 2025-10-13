package com.example.bus_ticket.service;

import com.example.bus_ticket.model.Receipt;
import com.example.bus_ticket.model.Trip;
import com.example.bus_ticket.model.User;
import com.example.bus_ticket.model.Role;
import com.example.bus_ticket.repository.TripRepository;
import com.example.bus_ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrazione nuovo utente
    public User registerUser(String email, String password, BigDecimal credit) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email già registrata");
        }

        User user = new User();
        user.setEmail(email);

        // Hash della password
        user.setPassword(passwordEncoder.encode(password));

        // Ruolo USER di default
        user.setRoles(Set.of(Role.USER));

        // Credito iniziale
        user.setCredit(credit != null ? credit : BigDecimal.ZERO);

        return userRepository.save(user);
    }

    // Recupera un utente per ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Aggiunge credito a un utente
    public User addCredit(Integer userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Importo non valido");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        user.setCredit(user.getCredit().add(amount));
        return userRepository.save(user);
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

        user.setCredit(user.getCredit().subtract(trip.getPrice()));
        userRepository.save(user);

        Receipt receipt = new Receipt();
        receipt.setUserId(user.getId());
        receipt.setTripId(trip.getId());
        receipt.setCharge(trip.getPrice());
        receipt.setRemainingCredit(user.getCredit());

        return receipt;
    }
}
