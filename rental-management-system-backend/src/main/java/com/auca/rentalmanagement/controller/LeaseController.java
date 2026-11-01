
package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.Lease;
import com.auca.rentalmanagement.model.LeaseStatus;
import com.auca.rentalmanagement.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    // Part A: Save lease
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Lease lease) {
        String message = leaseService.save(lease);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // Part A: Get all leases
    @GetMapping("/all")
    public ResponseEntity<List<Lease>> getAll() {
        return new ResponseEntity<>(leaseService.getAll(), HttpStatus.OK);
    }

    // B2: Find leases by status
    @GetMapping("/by-status")
    public ResponseEntity<List<Lease>> getByStatus(
            @RequestParam LeaseStatus status) {

        return new ResponseEntity<>(
                leaseService.getByStatus(status),
                HttpStatus.OK
        );
    }

    // B3: Find leases between two dates
    @GetMapping("/between")
    public ResponseEntity<List<Lease>> getBetweenDates(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return new ResponseEntity<>(
                leaseService.getBetweenDates(startDate, endDate),
                HttpStatus.OK
        );
    }

    // Part A: Get lease by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Lease> lease = leaseService.getById(id);

        if (lease.isPresent()) {
            return new ResponseEntity<>(lease.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "The lease with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    // Part A: Update lease
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Lease lease) {

        String message = leaseService.update(id, lease);

        if (message.equals("Lease updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Part A: Delete lease
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = leaseService.delete(id);

        if (message.equals("Lease deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}

