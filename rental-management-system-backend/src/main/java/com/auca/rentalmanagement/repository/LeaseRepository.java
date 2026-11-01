package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.LeaseStatus;
import com.auca.rentalmanagement.model.Caretaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {

    // B2: Find leases by status, ordered by start date
    List<Lease> findByStatusOrderByStartDateAsc(LeaseStatus status);

    // B3: Find leases between two dates
    List<Lease> findByStartDateBetweenOrderByStartDateAsc(
            LocalDate start,
            LocalDate end
    );

    // D1: Check for another non-rejected lease on the same date
    boolean existsByCaretakerAndStartDateAndStatusNot(
            Caretaker caretaker,
            LocalDate startDate,
            LeaseStatus status
    );

    // E3: Check whether a lease has rent payments
    // This method is in RentPaymentRepository because the relationship
    // is owned by RentPayment; see the repository implementation above.

    // F1: Count leases grouped by status
    @Query("SELECT l.status, COUNT(l) FROM Lease l GROUP BY l.status")
    List<Object[]> countLeasesByStatus();

    // F2: Reject leases for a caretaker on a particular date,
    // excluding leases that are already SIGNED
    @Modifying
    @Query("UPDATE Lease l SET l.status = :rejectedStatus " +
            "WHERE l.caretaker.id = :caretakerId " +
            "AND l.startDate = :date " +
            "AND l.status <> :signedStatus")
    int cancelLeasesByCaretakerAndDate(
            @Param("caretakerId") UUID caretakerId,
            @Param("date") LocalDate date,
            @Param("rejectedStatus") LeaseStatus rejectedStatus,
            @Param("signedStatus") LeaseStatus signedStatus
    );
}