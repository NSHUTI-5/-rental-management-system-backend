
package com.auca.rentalmanagement.controller;

import com.auca.rentalmanagement.model.Caretaker;
import com.auca.rentalmanagement.service.CaretakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/caretakers")
public class CaretakerController {

    @Autowired
    private CaretakerService caretakerService;

    // Save caretaker
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Caretaker caretaker) {
        return new ResponseEntity<>(
                caretakerService.save(caretaker), HttpStatus.CREATED);
    }

    // Get all caretakers
    @GetMapping("/all")
    public ResponseEntity<List<Caretaker>> getAll() {
        return new ResponseEntity<>(caretakerService.getAll(), HttpStatus.OK);
    }

    // C1: Find caretakers by maintenance skill
    @GetMapping("/by-maintenance-skill")
    public ResponseEntity<List<Caretaker>> getByMaintenanceSkill(
            @RequestParam String name) {

        return new ResponseEntity<>(
                caretakerService.getByMaintenanceSkill(name),
                HttpStatus.OK);
    }

    // Get caretaker by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Caretaker> caretaker = caretakerService.getById(id);

        if (caretaker.isPresent()) {
            return new ResponseEntity<>(caretaker.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "The caretaker with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }
    }

    // Update caretaker
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Caretaker caretaker) {

        String message = caretakerService.update(id, caretaker);

        if (message.equals("Caretaker updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Delete caretaker
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = caretakerService.delete(id);

        if (message.equals("Caretaker deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}

