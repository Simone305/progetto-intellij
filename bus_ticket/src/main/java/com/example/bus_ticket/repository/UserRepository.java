package com.example.bus_ticket.repository;

import com.example.bus_ticket.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email); // utile per login e registrazione
}
