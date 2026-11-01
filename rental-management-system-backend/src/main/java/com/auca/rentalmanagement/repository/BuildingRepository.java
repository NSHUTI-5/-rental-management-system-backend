
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BuildingRepository extends JpaRepository<Building, UUID> {

    // C2: Find buildings that have no caretaker
    @Query("SELECT b FROM Building b " +
           "WHERE NOT EXISTS (" +
           "SELECT c FROM Caretaker c WHERE c.building = b) " +
           "ORDER BY b.buildingNumber ASC")
    List<Building> findFreeBuildings();

    // D4: Find a building using its building number
    Building findByBuildingNumber(int buildingNumber);
}



