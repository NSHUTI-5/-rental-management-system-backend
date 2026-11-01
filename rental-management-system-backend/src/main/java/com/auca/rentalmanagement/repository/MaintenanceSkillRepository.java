
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.MaintenanceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MaintenanceSkillRepository
        extends JpaRepository<MaintenanceSkill, UUID> {

    // C3: Find maintenance skills that no caretaker holds
    @Query("SELECT ms FROM MaintenanceSkill ms " +
           "WHERE ms.caretakers IS EMPTY")
    List<MaintenanceSkill> findUnusedSkills();

}

