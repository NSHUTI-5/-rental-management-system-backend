package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.LeaseStatus;
import com.auca.rentalmanagement.model.RentPayment;
import com.auca.rentalmanagement.repository.LeaseRepository;
import com.auca.rentalmanagement.repository.RentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentPaymentService {

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    // E1: Save rent payment
    public String save(UUID leaseId, RentPayment payment) {

        Optional<Lease> found = leaseRepository.findById(leaseId);

        if (found.isEmpty()) {
            return "The lease with that id does not exist";
        }

        Lease lease = found.get();

        if (lease.getStatus() != LeaseStatus.SIGNED) {
            return "Rent payments are only for SIGNED leases";
        }

        payment.setId(null);
        payment.setPaidDate(LocalDate.now());
        payment.setLease(lease);

        rentPaymentRepository.save(payment);

        return "Rent payment saved successfully";
    }

    // E2: Get tenant rent payment history
    public List<RentPayment> getByTenantCode(String tenantCode) {
        return rentPaymentRepository.findByTenantCode(tenantCode);
    }

    // E3: Delete rent payment
    public String delete(UUID id) {

        if (!rentPaymentRepository.existsById(id)) {
            return "The rent payment with that id does not exist";
        }

        rentPaymentRepository.deleteById(id);

        return "Rent payment deleted successfully";
    }
}