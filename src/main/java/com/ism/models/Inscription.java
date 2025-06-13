package com.ism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inscription {
    private int id;
    private int etudiantId;
    private int classeId;
    private String anneeScolaire;
    private String statut; // ACTIVE, SUSPENDUE, ANNULEE
    private LocalDateTime dateInscription;

    // Constructeurs
    public Inscription() {
        this.statut = "ACTIVE";
        this.dateInscription = LocalDateTime.now();
    }

    public Inscription(int etudiantId, int classeId, String anneeScolaire) {
        this.etudiantId = etudiantId;
        this.classeId = classeId;
        this.anneeScolaire = anneeScolaire;
        this.statut = "ACTIVE";
        this.dateInscription = LocalDateTime.now();
    }

    // Méthodes utilitaires
    public String getDateToString() {
        return dateInscription.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getStatutLibelle() {
        switch (statut) {
            case "ACTIVE": return "✅ Active";
            case "SUSPENDUE": return "⏸️ Suspendue";
            case "ANNULEE": return "❌ Annulée";
            default: return statut;
        }
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEtudiantId() { return etudiantId; }
    public void setEtudiantId(int etudiantId) { this.etudiantId = etudiantId; }

    public int getClasseId() { return classeId; }
    public void setClasseId(int classeId) { this.classeId = classeId; }

    public String getAnneeScolaire() { return anneeScolaire; }
    public void setAnneeScolaire(String anneeScolaire) { this.anneeScolaire = anneeScolaire; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDateTime dateInscription) { this.dateInscription = dateInscription; }

    @Override
    public String toString() {
        return String.format("📝 Inscription %s - %s (%s)", anneeScolaire, getStatutLibelle(), getDateToString());
    }
}