package com.mycompany.interfazmuseo;

import java.time.LocalDate;
import java.util.List;

public class Ticket {
    private String visitorName;
    private String visitorEmail;
    private LocalDate visitDate;
    private List<Hall> halls;
    private double totalPrice;
    private String museumType;

    public Ticket(String visitorName, String visitorEmail, LocalDate visitDate, List<Hall> halls, double totalPrice, String museumType) {
        this.visitorName = visitorName;
        this.visitorEmail = visitorEmail;
        this.visitDate = visitDate;
        this.halls = halls;
        this.totalPrice = totalPrice;
        this.museumType = museumType;
    }

    public String getVisitorName() { return visitorName; }
    public String getVisitorEmail() { return visitorEmail; }
    public LocalDate getVisitDate() { return visitDate; }
    public List<Hall> getHalls() { return halls; }
    public double getTotalPrice() { return totalPrice; }
    public String getMuseumType() { return museumType; }
}
