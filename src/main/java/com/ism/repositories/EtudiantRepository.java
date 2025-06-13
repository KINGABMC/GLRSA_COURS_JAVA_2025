package com.ism.repositories;

import com.ism.models.Etudiant;
import com.ism.utils.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EtudiantRepository {
    private static final String FILENAME = "etudiants.json";
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public boolean save(Etudiant etudiant) {
        try {
            List<Etudiant> etudiants = findAll();
            etudiant.setId(idGenerator.getAndIncrement());
            etudiants.add(etudiant);
            JsonFileManager.writeList(FILENAME, etudiants);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = JsonFileManager.readList(FILENAME, new TypeReference<List<Etudiant>>() {});
        
        // Initialiser avec des données de test si le fichier est vide
        if (etudiants.isEmpty()) {
            initializeTestData();
            etudiants = JsonFileManager.readList(FILENAME, new TypeReference<List<Etudiant>>() {});
        }
        
        // Mettre à jour le générateur d'ID
        etudiants.forEach(etudiant -> {
            if (etudiant.getId() >= idGenerator.get()) {
                idGenerator.set(etudiant.getId() + 1);
            }
        });
        
        return etudiants;
    }

    public Etudiant findById(int id) {
        return findAll().stream()
                .filter(etudiant -> etudiant.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Etudiant findByMatricule(String matricule) {
        return findAll().stream()
                .filter(etudiant -> etudiant.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);
    }

    public List<Etudiant> findByNom(String nom) {
        return findAll().stream()
                .filter(etudiant -> 
                    etudiant.getNom().toLowerCase().contains(nom.toLowerCase()) ||
                    etudiant.getPrenom().toLowerCase().contains(nom.toLowerCase()))
                .toList();
    }

    private void initializeTestData() {
        List<Etudiant> etudiants = List.of(
            new Etudiant("SARR", "Aissatou", "Dakar, Plateau", "F"),
            new Etudiant("BA", "Mamadou", "Thiès, Centre", "M"),
            new Etudiant("SECK", "Mariama", "Saint-Louis, Nord", "F"),
            new Etudiant("DIOUF", "Ibrahima", "Kaolack, Centre", "M"),
            new Etudiant("KANE", "Aminata", "Ziguinchor, Sud", "F")
        );
        
        // Définir des matricules fixes pour les tests
        String[] matricules = {"ISM2024001", "ISM2024002", "ISM2024003", "ISM2024004", "ISM2024005"};
        
        for (int i = 0; i < etudiants.size(); i++) {
            etudiants.get(i).setId(i + 1);
            etudiants.get(i).setMatricule(matricules[i]);
        }
        
        JsonFileManager.writeList(FILENAME, etudiants);
        idGenerator.set(etudiants.size() + 1);
    }
}