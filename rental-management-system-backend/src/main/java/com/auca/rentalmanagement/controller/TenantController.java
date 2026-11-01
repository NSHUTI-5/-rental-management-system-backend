
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

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Tenant tenant) {
        return new ResponseEntity<>(tenantService.save(tenant), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getAll() {
        return new ResponseEntity<>(tenantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Tenant> tenant = tenantService.getById(id);

        if (tenant.isPresent()) {
            return new ResponseEntity<>(tenant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The tenant with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Tenant tenant) {
        String message = tenantService.update(id, tenant);

        if (message.equals("Tenant updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

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

