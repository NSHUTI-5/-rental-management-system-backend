package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.LeaseStatus;
import com.auca.rentalmanagement.repository.LeaseRepository;
import com.auca.rentalmanagement.repository.RentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    // D1: Save lease with business rules
    public String save(Lease lease) {

        if (lease.getStartDate().isBefore(LocalDate.now())) {
            return "Lease date cannot be in the past";
        }

        if (lease.getCaretaker() != null &&
                leaseRepository.existsByCaretakerAndStartDateAndStatusNot(
                        lease.getCaretaker(),
                        lease.getStartDate(),
                        LeaseStatus.REJECTED)) {

            return "Caretaker already has a lease starting on that date";
        }

        lease.setStatus(LeaseStatus.APPLIED);
        lease.setId(null);

        leaseRepository.save(lease);

        return "Lease saved successfully";
    }

    // Part A: Get all leases
    public List<Lease> getAll() {
        return leaseRepository.findAll();
    }

    // B2: Get leases by status
    public List<Lease> getByStatus(LeaseStatus status) {
        return leaseRepository.findByStatusOrderByStartDateAsc(status);
    }

    // B3: Get leases between dates
    public List<Lease> getBetweenDates(
            LocalDate start, LocalDate end) {

        return leaseRepository.findByStartDateBetweenOrderByStartDateAsc(
                start, end);
    }

    // Part A: Get lease by ID
    public Optional<Lease> getById(UUID id) {
        return leaseRepository.findById(id);
    }

    // Part A: Update lease without changing its status
    public String update(UUID id, Lease body) {

        Optional<Lease> found = leaseRepository.findById(id);

        if (found.isEmpty()) {
            return "The lease with that id does not exist";
        }

        Lease lease = found.get();

        lease.setStartDate(body.getStartDate());
        lease.setMonthlyRent(body.getMonthlyRent());
        lease.setUnitLabel(body.getUnitLabel());

        if (body.getTenant() != null) {
            lease.setTenant(body.getTenant());
        }

        if (body.getCaretaker() != null) {
            lease.setCaretaker(body.getCaretaker());
        }

        leaseRepository.save(lease);

        return "Lease updated successfully";
    }

    // D2: Change lease status using allowed transitions
    public String changeStatus(UUID id, LeaseStatus newStatus) {

        Optional<Lease> found = leaseRepository.findById(id);

        if (found.isEmpty()) {
            return "The lease with that id does not exist";
        }

        Lease lease = found.get();
        LeaseStatus currentStatus = lease.getStatus();

        boolean allowed = false;

        if (currentStatus == LeaseStatus.APPLIED &&
                (newStatus == LeaseStatus.APPROVED ||
                 newStatus == LeaseStatus.REJECTED)) {

            allowed = true;

        } else if (currentStatus == LeaseStatus.APPROVED &&
                (newStatus == LeaseStatus.SIGNED ||
                 newStatus == LeaseStatus.REJECTED)) {

            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from " + currentStatus +
                    " to " + newStatus;
        }

        lease.setStatus(newStatus);
        leaseRepository.save(lease);

        return "Lease status updated successfully";
    }

    // E3: Delete lease only when it has no rent payments
    public String delete(UUID id) {

        Optional<Lease> found = leaseRepository.findById(id);

        if (found.isEmpty()) {
            return "The lease with that id does not exist";
        }

        Lease lease = found.get();

        if (rentPaymentRepository.existsByLease(lease)) {
            return "That lease has rent payments, delete them first";
        }

        leaseRepository.delete(lease);

        return "Lease deleted successfully";
    }

    // F1: Get lease counts grouped by status
    public List<Object[]> getLeaseStatsByStatus() {
        return leaseRepository.countLeasesByStatus();
    }

    // F2: Cancel leases for one caretaker and date
    @Transactional
    public int cancelLeasesByCaretakerAndDate(
            UUID caretakerId, LocalDate date) {

        return leaseRepository.cancelLeasesByCaretakerAndDate(
                caretakerId,
                date,
                LeaseStatus.REJECTED,
                LeaseStatus.SIGNED
        );
    }
}