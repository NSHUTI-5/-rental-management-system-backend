package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BuildingRepository extends JpaRepository<Building, UUID> {

   
}
