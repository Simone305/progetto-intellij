package com.example.bus_ticket.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.HashSet;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal credit = BigDecimal.ZERO;

    // Ruoli come enum salvati come stringhe
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = Set.of(Role.USER); // USER di default

    // Costruttore vuoto
    public User() {
    }

    // Costruttore con parametri (opzionale)
    public User(String email, String password, BigDecimal credit) {
        this.email = email;
        this.password = password;
        this.credit = credit != null ? credit : BigDecimal.ZERO;
    }

    // Getter e setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
