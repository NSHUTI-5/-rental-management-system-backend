package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Caretaker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CaretakerRepository extends JpaRepository<Caretaker, UUID> {

  
}
