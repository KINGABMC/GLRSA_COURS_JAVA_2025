package com.ism.repositories;

import com.ism.models.User;
import com.ism.utils.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository {
    private static final String FILENAME = "users.json";
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public boolean save(User user) {
        try {
            List<User> users = findAll();
            user.setId(idGenerator.getAndIncrement());
            users.add(user);
            JsonFileManager.writeList(FILENAME, users);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> findAll() {
        List<User> users = JsonFileManager.readList(FILENAME, new TypeReference<List<User>>() {});
        
        // Initialiser avec des données de test si le fichier est vide
        if (users.isEmpty()) {
            initializeTestData();
            users = JsonFileManager.readList(FILENAME, new TypeReference<List<User>>() {});
        }
        
        // Mettre à jour le générateur d'ID
        users.forEach(user -> {
            if (user.getId() >= idGenerator.get()) {
                idGenerator.set(user.getId() + 1);
            }
        });
        
        return users;
    }

    public User findById(int id) {
        return findAll().stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> findByRole(String role) {
        return findAll().stream()
                .filter(user -> user.getRole().equals(role))
                .toList();
    }

    public long countByRole(String role) {
        return findAll().stream()
                .filter(user -> user.getRole().equals(role))
                .count();
    }

    private void initializeTestData() {
        List<User> users = List.of(
            new User("DIOP", "Amadou", "rp@ism.sn", "password", "RP"),
            new User("FALL", "Fatou", "attache@ism.sn", "password", "ATTACHE"),
            new User("NDIAYE", "Moussa", "prof@ism.sn", "password", "PROFESSEUR")
        );
        
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setId(i + 1);
        }
        
        JsonFileManager.writeList(FILENAME, users);
        idGenerator.set(users.size() + 1);
    }
}