package com.example.bus_ticket.controller;

import com.example.bus_ticket.model.User;
import com.example.bus_ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Registrazione nuovo utente
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(
                user.getEmail(),
                user.getPassword(),
                user.getCredit()
        );
    }

    // Recupera dati di un utente per ID
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Ricarica credito di un utente
    @PatchMapping("/{id}/credit/toup")
    public User addCredit(@PathVariable Integer id,
                          @RequestParam BigDecimal amount) {
        return userService.addCredit(id, amount);
    }
}
