package com.clothDonation.rewear.service;

import com.clothDonation.rewear.entities.User;
import com.clothDonation.rewear.repositories.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public boolean loginUser(User user) {
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
        return existingUser.isPresent() &&
                passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword());
    }

    public List<User> getUsers() {
        List<User> allUsers=userRepo.findAll();
        return allUsers;
    }
}
