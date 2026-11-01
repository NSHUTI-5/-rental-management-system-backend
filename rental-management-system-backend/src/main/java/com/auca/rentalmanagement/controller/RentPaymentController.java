package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.RentPayment;
import com.auca.rentalmanagement.service.RentPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rent-payments")
public class RentPaymentController {

    @Autowired
    private RentPaymentService rentPaymentService;

    // E1: Save rent payment
    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestParam UUID leaseId,
            @RequestBody RentPayment payment) {

        String message = rentPaymentService.save(leaseId, payment);

        if (message.equals("Rent payment saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals(
                "The lease with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    // E2: Get all rent payments belonging to a tenant
    @GetMapping("/tenant/{tenantCode}")
    public ResponseEntity<List<RentPayment>> getByTenantCode(
            @PathVariable String tenantCode) {

        return new ResponseEntity<>(
                rentPaymentService.getByTenantCode(tenantCode),
                HttpStatus.OK);
    }

    // E3: Delete rent payment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String message = rentPaymentService.delete(id);

        if (message.equals("Rent payment deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}