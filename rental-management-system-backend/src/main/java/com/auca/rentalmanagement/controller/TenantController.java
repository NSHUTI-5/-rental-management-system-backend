
package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.Tenant;
import com.auca.rentalmanagement.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    // Part A: Save tenant
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Tenant tenant) {
        return new ResponseEntity<>(tenantService.save(tenant), HttpStatus.CREATED);
    }

    // Part A: Get all tenants
    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getAll() {
        return new ResponseEntity<>(tenantService.getAll(), HttpStatus.OK);
    }

    // B1: Find tenants by last name
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Tenant>> getByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(
                tenantService.getByLastName(lastName),
                HttpStatus.OK);
    }

    // C4: Get tenants of a particular caretaker
    @GetMapping("/of-caretaker/{caretakerId}")
    public ResponseEntity<?> getTenantsOfCaretaker(
            @PathVariable UUID caretakerId) {

        if (!tenantService.caretakerExists(caretakerId)) {
            return new ResponseEntity<>(
                    "The caretaker with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                tenantService.getTenantsOfCaretaker(caretakerId),
                HttpStatus.OK);
    }

    // Part A: Get tenant by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Tenant> tenant = tenantService.getById(id);

        if (tenant.isPresent()) {
            return new ResponseEntity<>(tenant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The tenant with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    // Part A: Update tenant
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Tenant tenant) {
        String message = tenantService.update(id, tenant);

        if (message.equals("Tenant updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Part A: Delete tenant
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = tenantService.delete(id);

        if (message.equals("Tenant deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
