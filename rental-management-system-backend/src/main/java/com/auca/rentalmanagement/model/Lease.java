package com.auca.rentalmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lease")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private LeaseStatus status;

    private double monthlyRent;
    private String unitLabel;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "caretaker_id")
    private Caretaker caretaker;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LeaseStatus getStatus() { return status; }
    public void setStatus(LeaseStatus status) { this.status = status; }
    public double getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(double monthlyRent) { this.monthlyRent = monthlyRent; }
    public String getUnitLabel() { return unitLabel; }
    public void setUnitLabel(String unitLabel) { this.unitLabel = unitLabel; }
    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }
    public Caretaker getCaretaker() { return caretaker; }
    public void setCaretaker(Caretaker caretaker) { this.caretaker = caretaker; }
}
