
package com.auca.rentalmanagement.service;

import com.auca.rentalmanagement.model.Tenant;
import com.auca.rentalmanagement.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    // Save tenant
    public String save(Tenant tenant) {
        tenant.setId(null);
        tenantRepository.save(tenant);
        return "Tenant saved successfully";
    }

    // Get all tenants
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    // Get tenant by ID
    public Optional<Tenant> getById(UUID id) {
        return tenantRepository.findById(id);
    }

    // Update tenant
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

    // Delete tenant
    public String delete(UUID id) {
        if (!tenantRepository.existsById(id)) {
            return "The tenant with that id does not exist";
        }

        tenantRepository.deleteById(id);
        return "Tenant deleted successfully";
    }
}
