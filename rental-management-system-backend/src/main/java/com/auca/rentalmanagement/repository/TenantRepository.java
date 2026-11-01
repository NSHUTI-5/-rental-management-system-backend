package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    
}
