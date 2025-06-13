package com.ism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Demande {
    private int id;
    private int etudiantId;
    private String type; // SUSPENSION, ANNULATION
    private String motif;
    private String etat; // EN_ATTENTE, ACCEPTEE, REFUSEE
    private LocalDateTime dateDemande;
    private LocalDateTime dateTraitement;
    private int traiteParId;

    // Constructeurs
    public Demande() {
        this.etat = "EN_ATTENTE";
        this.dateDemande = LocalDateTime.now();
    }

    public Demande(int etudiantId, String type, String motif) {
        this.etudiantId = etudiantId;
        this.type = type;
        this.motif = motif;
        this.etat = "EN_ATTENTE";
        this.dateDemande = LocalDateTime.now();
    }

    // Méthodes utilitaires
    public String getDateDemandeToString() {
        return dateDemande.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getDateTraitementToString() {
        return dateTraitement != null ? dateTraitement.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) : "Non traitée";
    }

    public String getTypeLibelle() {
        return "SUSPENSION".equals(type) ? "🔄 Suspension" : "❌ Annulation";
    }

    public String getEtatLibelle() {
        switch (etat) {
            case "EN_ATTENTE": return "⏳ En attente";
            case "ACCEPTEE": return "✅ Acceptée";
            case "REFUSEE": return "❌ Refusée";
            default: return etat;
        }
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEtudiantId() { return etudiantId; }
    public void setEtudiantId(int etudiantId) { this.etudiantId = etudiantId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public LocalDateTime getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDateTime dateDemande) { this.dateDemande = dateDemande; }

    public LocalDateTime getDateTraitement() { return dateTraitement; }
    public void setDateTraitement(LocalDateTime dateTraitement) { this.dateTraitement = dateTraitement; }

    public int getTraiteParId() { return traiteParId; }
    public void setTraiteParId(int traiteParId) { this.traiteParId = traiteParId; }

    @Override
    public String toString() {
        return String.format("📋 %s - %s (%s) - %s", getTypeLibelle(), getEtatLibelle(), getDateDemandeToString(), motif);
    }
}