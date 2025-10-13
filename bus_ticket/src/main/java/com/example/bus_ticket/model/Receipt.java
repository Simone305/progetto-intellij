package com.example.bus_ticket.model;

import java.math.BigDecimal;

public class Receipt {
    private Integer userId;
    private Integer tripId;
    private BigDecimal charge;
    private BigDecimal remainingCredit;

    // getter e setter
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTripId() { return tripId; }
    public void setTripId(Integer tripId) { this.tripId = tripId; }

    public BigDecimal getCharge() { return charge; }
    public void setCharge(BigDecimal charge) { this.charge = charge; }

    public BigDecimal getRemainingCredit() { return remainingCredit; }
    public void setRemainingCredit(BigDecimal remainingCredit) { this.remainingCredit = remainingCredit; }
}

