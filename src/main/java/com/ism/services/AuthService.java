package com.ism.services;

import com.ism.models.User;
import com.ism.repositories.UserRepository;

public class AuthService {
    private UserRepository userRepository = new UserRepository();

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        // Comparaison directe des mots de passe en clair
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(User user) {
        return userRepository.save(user);
    }
}