package com.ism.services;

import com.ism.models.Etudiant;
import com.ism.repositories.EtudiantRepository;
import java.util.List;

public class EtudiantService {
    private EtudiantRepository etudiantRepository = new EtudiantRepository();

    public void addEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    public List<Etudiant> getEtudiants(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return etudiantRepository.findAll();
        }
        return etudiantRepository.findByNom(nom);
    }

    public Etudiant getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    public Etudiant getEtudiantById(int id) {
        return etudiantRepository.findById(id);
    }
}