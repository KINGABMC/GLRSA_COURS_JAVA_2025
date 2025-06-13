package com.ism.services;

import com.ism.models.Demande;
import com.ism.repositories.DemandeRepository;
import java.time.LocalDateTime;
import java.util.List;

public class DemandeService {
    private DemandeRepository demandeRepository = new DemandeRepository();

    public void addDemande(Demande demande) {
        demandeRepository.save(demande);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public List<Demande> getDemandesParEtat(String etat) {
        return demandeRepository.findByEtat(etat);
    }

    public List<Demande> getDemandesTraitees() {
        return demandeRepository.findTraitees();
    }

    public List<Demande> getDemandesParEtudiant(int etudiantId) {
        return demandeRepository.findByEtudiant(etudiantId);
    }

    public boolean traiterDemande(int demandeId, String decision, int traiteParId) {
        Demande demande = demandeRepository.findById(demandeId);
        if (demande != null && "EN_ATTENTE".equals(demande.getEtat())) {
            demande.setEtat(decision);
            demande.setDateTraitement(LocalDateTime.now());
            demande.setTraiteParId(traiteParId);
            return demandeRepository.update(demande);
        }
        return false;
    }
}