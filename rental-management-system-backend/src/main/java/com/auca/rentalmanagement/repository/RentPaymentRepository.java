package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.RentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RentPaymentRepository
        extends JpaRepository<RentPayment, UUID> {

    // E2: Find all payments belonging to a tenant, newest first
    @Query("SELECT d FROM RentPayment d " +
            "WHERE d.lease.tenant.tenantCode = :tenantCode " +
            "ORDER BY d.paidDate DESC")
    List<RentPayment> findByTenantCode(
            @Param("tenantCode") String tenantCode);

    // E3: Check whether a lease has rent payments
    boolean existsByLease(Lease lease);
}