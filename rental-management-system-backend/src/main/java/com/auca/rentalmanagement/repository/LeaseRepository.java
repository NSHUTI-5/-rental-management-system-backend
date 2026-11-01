
package com.auca.rentalmanagement.repository;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {

    // B2: Find leases by status, ordered by start date
    List<Lease> findByStatusOrderByStartDateAsc(LeaseStatus status);

    // B3: Find leases between two dates, including both dates
    List<Lease> findByStartDateBetweenOrderByStartDateAsc(
            LocalDate start,
            LocalDate end
    );

}
