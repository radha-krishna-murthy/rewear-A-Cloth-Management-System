package com.clothDonation.rewear.repositories;

import com.clothDonation.rewear.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepo extends JpaRepository<Donor,Long> {
}
