package com.ism.repositories;

import com.ism.models.Classe;
import com.ism.utils.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClasseRepository {
    private static final String FILENAME = "classes.json";
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public boolean save(Classe classe) {
        try {
            List<Classe> classes = findAll();
            classe.setId(idGenerator.getAndIncrement());
            classes.add(classe);
            JsonFileManager.writeList(FILENAME, classes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Classe> findAll() {
        List<Classe> classes = JsonFileManager.readList(FILENAME, new TypeReference<List<Classe>>() {});
        
        // Initialiser avec des données de test si le fichier est vide
        if (classes.isEmpty()) {
            initializeTestData();
            classes = JsonFileManager.readList(FILENAME, new TypeReference<List<Classe>>() {});
        }
        
        // Mettre à jour le générateur d'ID
        classes.forEach(classe -> {
            if (classe.getId() >= idGenerator.get()) {
                idGenerator.set(classe.getId() + 1);
            }
        });
        
        return classes;
    }

    public Classe findById(int id) {
        return findAll().stream()
                .filter(classe -> classe.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Classe> findByFiliere(String filiere) {
        return findAll().stream()
                .filter(classe -> classe.getFiliere().toLowerCase().contains(filiere.toLowerCase()))
                .toList();
    }

    private void initializeTestData() {
        List<Classe> classes = List.of(
            new Classe("L3 Informatique A", "Informatique", "L3"),
            new Classe("L3 Informatique B", "Informatique", "L3"),
            new Classe("L2 Gestion A", "Gestion", "L2"),
            new Classe("M1 Marketing", "Marketing", "M1"),
            new Classe("L1 Comptabilité", "Comptabilité", "L1")
        );
        
        for (int i = 0; i < classes.size(); i++) {
            classes.get(i).setId(i + 1);
        }
        
        JsonFileManager.writeList(FILENAME, classes);
        idGenerator.set(classes.size() + 1);
    }
}