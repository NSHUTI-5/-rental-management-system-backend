
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Caretaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CaretakerRepository extends JpaRepository<Caretaker, UUID> {

    // C1: Find caretakers by maintenance skill, ignoring case
    @Query("SELECT DISTINCT c FROM Caretaker c JOIN c.maintenanceSkills ms " +
           "WHERE LOWER(ms.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Caretaker> findByMaintenanceSkillName(@Param("name") String name);

}

