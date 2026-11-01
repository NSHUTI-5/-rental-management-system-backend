package com.auca.rentalmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "maintenance_skill")
public class MaintenanceSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    // inverse side: the owner is Caretaker.maintenanceSkills; @JsonIgnore stops the JSON loop
    @ManyToMany(mappedBy = "maintenanceSkills")
    @JsonIgnore
    private List<Caretaker> caretakers;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Caretaker> getCaretakers() { return caretakers; }
    public void setCaretakers(List<Caretaker> caretakers) { this.caretakers = caretakers; }
}
