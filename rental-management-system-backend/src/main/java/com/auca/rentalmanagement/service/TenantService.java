
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Tenant;
import com.auca.rentalmanagement.repository.TenantRepository;
import com.auca.rentalmanagement.repository.CaretakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private CaretakerRepository caretakerRepository;

    // Part A: Save tenant
    public String save(Tenant tenant) {
        tenant.setId(null);
        tenantRepository.save(tenant);
        return "Tenant saved successfully";
    }

    // Part A: Get all tenants
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    // B1: Find tenants by last name
    public List<Tenant> getByLastName(String lastName) {
        return tenantRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    // C4: Check whether the caretaker exists
    public boolean caretakerExists(UUID caretakerId) {
        return caretakerRepository.existsById(caretakerId);
    }

    // C4: Get unique tenants belonging to a caretaker's leases
    public List<Tenant> getTenantsOfCaretaker(UUID caretakerId) {
        return tenantRepository.findDistinctTenantsByCaretakerId(caretakerId);
    }

    // Part A: Get tenant by ID
    public Optional<Tenant> getById(UUID id) {
        return tenantRepository.findById(id);
    }

    // Part A: Update tenant
    public String update(UUID id, Tenant body) {
        Optional<Tenant> found = tenantRepository.findById(id);

        if (found.isEmpty()) {
            return "The tenant with that id does not exist";
        }

        Tenant tenant = found.get();
        tenant.setFirstName(body.getFirstName());
        tenant.setLastName(body.getLastName());
        tenant.setTenantCode(body.getTenantCode());
        tenant.setPhone(body.getPhone());
        tenant.setOccupation(body.getOccupation());

        tenantRepository.save(tenant);
        return "Tenant updated successfully";
    }

    // Part A: Delete tenant
    public String delete(UUID id) {
        if (!tenantRepository.existsById(id)) {
            return "The tenant with that id does not exist";
        }

        tenantRepository.deleteById(id);
        return "Tenant deleted successfully";
    }
}

