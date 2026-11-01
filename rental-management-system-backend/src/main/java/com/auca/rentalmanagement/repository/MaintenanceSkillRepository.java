package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.MaintenanceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MaintenanceSkillRepository extends JpaRepository<MaintenanceSkill, UUID> {

   
}
