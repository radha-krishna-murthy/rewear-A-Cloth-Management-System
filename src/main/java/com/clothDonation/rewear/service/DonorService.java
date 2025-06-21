package com.clothDonation.rewear.service;

import com.clothDonation.rewear.entities.Donor;
import com.clothDonation.rewear.repositories.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DonorService {

    private final DonorRepo donorRepo;

    @Autowired
    public DonorService(DonorRepo donorRepo) {
        this.donorRepo = donorRepo;
    }

    // POST
    public Donor saveDonor(Donor donor) {
        return donorRepo.save(donor);
    }

    // GET by ID
    public Donor getDonorById(Long donorId) {
        return donorRepo.findById(donorId).orElse(null);
    }

    // GET all
    public List<Donor> getAllDonors() {
        return donorRepo.findAll();
    }

    // DELETE
    public void deleteDonorById(Long donorId) {
        donorRepo.deleteById(donorId);
    }
}
