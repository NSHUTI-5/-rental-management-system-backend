package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {

    
}
