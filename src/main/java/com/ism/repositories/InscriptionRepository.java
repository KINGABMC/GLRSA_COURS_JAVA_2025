package com.ism.repositories;

import com.ism.models.Inscription;
import com.ism.utils.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InscriptionRepository {
    private static final String FILENAME = "inscriptions.json";
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public boolean save(Inscription inscription) {
        try {
            List<Inscription> inscriptions = findAll();
            inscription.setId(idGenerator.getAndIncrement());
            inscriptions.add(inscription);
            JsonFileManager.writeList(FILENAME, inscriptions);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Inscription> findAll() {
        List<Inscription> inscriptions = JsonFileManager.readList(FILENAME, new TypeReference<List<Inscription>>() {});
        
        // Initialiser avec des données de test si le fichier est vide
        if (inscriptions.isEmpty()) {
            initializeTestData();
            inscriptions = JsonFileManager.readList(FILENAME, new TypeReference<List<Inscription>>() {});
        }
        
        // Mettre à jour le générateur d'ID
        inscriptions.forEach(inscription -> {
            if (inscription.getId() >= idGenerator.get()) {
                idGenerator.set(inscription.getId() + 1);
            }
        });
        
        return inscriptions;
    }

    public Inscription findById(int id) {
        return findAll().stream()
                .filter(inscription -> inscription.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Inscription> findByClasseAndAnnee(int classeId, String anneeScolaire) {
        return findAll().stream()
                .filter(inscription -> 
                    inscription.getClasseId() == classeId && 
                    inscription.getAnneeScolaire().equals(anneeScolaire) &&
                    "ACTIVE".equals(inscription.getStatut()))
                .toList();
    }

    public boolean existsByEtudiantAndAnnee(int etudiantId, String anneeScolaire) {
        return findAll().stream()
                .anyMatch(inscription -> 
                    inscription.getEtudiantId() == etudiantId && 
                    inscription.getAnneeScolaire().equals(anneeScolaire));
    }

    private void initializeTestData() {
        List<Inscription> inscriptions = List.of(
            new Inscription(1, 1, "2024-2025"),
            new Inscription(2, 1, "2024-2025"),
            new Inscription(3, 2, "2024-2025"),
            new Inscription(4, 3, "2024-2025"),
            new Inscription(5, 4, "2024-2025")
        );
        
        for (int i = 0; i < inscriptions.size(); i++) {
            inscriptions.get(i).setId(i + 1);
        }
        
        JsonFileManager.writeList(FILENAME, inscriptions);
        idGenerator.set(inscriptions.size() + 1);
    }
}