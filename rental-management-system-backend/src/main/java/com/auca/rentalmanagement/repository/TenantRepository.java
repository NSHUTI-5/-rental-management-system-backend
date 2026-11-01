
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    // B1: Find tenants by last name, ignoring case, sorted by first name
    List<Tenant> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    // C4: Find unique tenants who have leases with a particular caretaker
    @Query("SELECT DISTINCT l.tenant FROM Lease l " +
           "WHERE l.caretaker.id = :caretakerId")
    List<Tenant> findDistinctTenantsByCaretakerId(
            @Param("caretakerId") UUID caretakerId);

}


