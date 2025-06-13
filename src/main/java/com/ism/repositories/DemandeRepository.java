package com.ism.repositories;

import com.ism.models.Demande;
import com.ism.utils.JsonFileManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DemandeRepository {
    private static final String FILENAME = "demandes.json";
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public boolean save(Demande demande) {
        try {
            List<Demande> demandes = findAll();
            demande.setId(idGenerator.getAndIncrement());
            demandes.add(demande);
            JsonFileManager.writeList(FILENAME, demandes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Demande demande) {
        try {
            List<Demande> demandes = findAll();
            for (int i = 0; i < demandes.size(); i++) {
                if (demandes.get(i).getId() == demande.getId()) {
                    demandes.set(i, demande);
                    JsonFileManager.writeList(FILENAME, demandes);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Demande> findAll() {
        List<Demande> demandes = JsonFileManager.readList(FILENAME, new TypeReference<List<Demande>>() {});
        
        // Mettre à jour le générateur d'ID
        demandes.forEach(demande -> {
            if (demande.getId() >= idGenerator.get()) {
                idGenerator.set(demande.getId() + 1);
            }
        });
        
        return demandes;
    }

    public Demande findById(int id) {
        return findAll().stream()
                .filter(demande -> demande.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Demande> findByEtat(String etat) {
        return findAll().stream()
                .filter(demande -> demande.getEtat().equals(etat))
                .toList();
    }

    public List<Demande> findTraitees() {
        return findAll().stream()
                .filter(demande -> !"EN_ATTENTE".equals(demande.getEtat()))
                .toList();
    }

    public List<Demande> findByEtudiant(int etudiantId) {
        return findAll().stream()
                .filter(demande -> demande.getEtudiantId() == etudiantId)
                .toList();
    }
}