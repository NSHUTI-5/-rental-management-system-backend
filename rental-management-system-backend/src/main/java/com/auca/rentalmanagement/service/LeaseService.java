
package com.auca.rentalmanagement.service;
import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    // Save lease
    public String save(Lease lease) {
        lease.setId(null);
        leaseRepository.save(lease);
        return "Lease saved successfully";
    }

    // Get all leases
    public List<Lease> getAll() {
        return leaseRepository.findAll();
    }

    // Get lease by ID
    public Optional<Lease> getById(UUID id) {
        return leaseRepository.findById(id);
    }

    // Update lease
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

    // Delete lease
    public String delete(UUID id) {
        if (!leaseRepository.existsById(id)) {
            return "The lease with that id does not exist";
        }

        leaseRepository.deleteById(id);
        return "Lease deleted successfully";
    }
}
