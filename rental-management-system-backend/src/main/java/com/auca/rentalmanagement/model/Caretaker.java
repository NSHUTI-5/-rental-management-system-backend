package com.auca.rentalmanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "caretaker")
public class Caretaker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    // owning side of the many-to-many
    @ManyToMany
    @JoinTable(
            name = "caretaker_maintenance_skill",
            joinColumns = @JoinColumn(name = "caretaker_id"),
            inverseJoinColumns = @JoinColumn(name = "maintenance_skill_id"))
    private List<MaintenanceSkill> maintenanceSkills = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }
    public List<MaintenanceSkill> getMaintenanceSkills() { return maintenanceSkills; }
    public void setMaintenanceSkills(List<MaintenanceSkill> maintenanceSkills) { this.maintenanceSkills = maintenanceSkills; }
}
