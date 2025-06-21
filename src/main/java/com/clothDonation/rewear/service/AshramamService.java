package com.clothDonation.rewear.service;

import com.clothDonation.rewear.entities.Ashramam;
import com.clothDonation.rewear.repositories.AshramamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AshramamService {

    private final AshramamRepo ashramamRepo;

    @Autowired
    public AshramamService(AshramamRepo ashramamRepo) {
        this.ashramamRepo = ashramamRepo;
    }

    // POST
    public Ashramam saveAshramam(Ashramam ashramam) {
        return ashramamRepo.save(ashramam);
    }

    // GET by ID
    public Ashramam getAshramamByID(Long ashramamId) {
        return ashramamRepo.findById(ashramamId).orElse(null);
    }

    // GET all
    public List<Ashramam> getAllAshramams() {
        return this.ashramamRepo.findAll();
    }

    // DELETE
    public void deleteAshramamById(Long ashramamId) {
        this.ashramamRepo.deleteById(ashramamId);
    }
}
