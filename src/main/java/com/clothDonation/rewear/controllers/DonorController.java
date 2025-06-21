package com.clothDonation.rewear.controllers;

import com.clothDonation.rewear.entities.Donor;
import com.clothDonation.rewear.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
public class DonorController {

    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDonor(@RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.saveDonor(donor));
    }

    @GetMapping("/{donorId}")
    public ResponseEntity<?> getDonor(@PathVariable long donorId) {
        Donor donor = donorService.getDonorById(donorId);
        return ResponseEntity.ok(donor);
    }
    @GetMapping("/allDonors")
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> list = donorService.getAllDonors();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{donorId}")
    public ResponseEntity<?> deleteDonor(@PathVariable long donorId) {
        donorService.deleteDonorById(donorId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{donorId}")
    public ResponseEntity<?> updateDonor(@PathVariable long donorId, @RequestBody Donor donor) {
        Donor existingDonor = donorService.getDonorById(donorId);
        if (existingDonor != null) {
            // Update fields from request
            existingDonor.setName(donor.getName());
            existingDonor.setEmail(donor.getEmail());
            existingDonor.setItemsDonated(donor.getItemsDonated());
            existingDonor.setCity(donor.getCity());

            Donor updatedDonor = donorService.saveDonor(existingDonor);
            return ResponseEntity.ok(updatedDonor);
        }
        return ResponseEntity.badRequest().body("Donor not found.");
    }
}
