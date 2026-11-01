
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    // B1: Find tenants by last name, ignoring case, sorted by first name
    List<Tenant> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

}

