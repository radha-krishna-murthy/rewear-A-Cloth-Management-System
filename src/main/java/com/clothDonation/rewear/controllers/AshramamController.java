package com.clothDonation.rewear.controllers;

import com.clothDonation.rewear.entities.Ashramam;
import com.clothDonation.rewear.service.AshramamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ashramam")
public class AshramamController {

    private final AshramamService ashramamService;

    @Autowired
    public AshramamController(AshramamService ashramamService) {
        this.ashramamService = ashramamService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAshramam(@RequestBody Ashramam ashramam) {
        return ResponseEntity.ok(ashramamService.saveAshramam(ashramam));
    }

    @GetMapping("/{ashramamId}")
    public ResponseEntity<?> getAshramam(@PathVariable long ashramamId) {
        Ashramam ashramam = ashramamService.getAshramamByID(ashramamId);
        return ResponseEntity.ok(ashramam);
    }

    @GetMapping("/allAshramams")
    public ResponseEntity<List<Ashramam>> getAllAshramams() {
        List<Ashramam> list = ashramamService.getAllAshramams();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{ashramamId}")
    public ResponseEntity<?> deleteAshramam(@PathVariable long ashramamId) {
        ashramamService.deleteAshramamById(ashramamId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{ashramamId}")
    public ResponseEntity<?> updateAshramam(@PathVariable long ashramamId, @RequestBody Ashramam ashramam) {
        Ashramam existingAshramam = ashramamService.getAshramamByID(ashramamId);
        if (existingAshramam != null) {
            existingAshramam.setName(ashramam.getName());
            existingAshramam.setLocation(ashramam.getLocation());

            Ashramam updatedAshramam = ashramamService.saveAshramam(existingAshramam);
            return ResponseEntity.ok(updatedAshramam);
        }
        return ResponseEntity.badRequest().body("Ashramam not found.");
    }
}
