package com.ism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Etudiant {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private String sexe; // M ou F
    private LocalDateTime dateNaissance;
    private LocalDateTime dateCreation;

    // Constructeurs
    public Etudiant() {
        this.matricule = generateMatricule();
        this.dateCreation = LocalDateTime.now();
    }

    public Etudiant(String nom, String prenom, String adresse, String sexe) {
        this.matricule = generateMatricule();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.sexe = sexe;
        this.dateCreation = LocalDateTime.now();
    }

    // Méthodes utilitaires
    private String generateMatricule() {
        return "ISM" + LocalDateTime.now().getYear() + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public String getDateToString() {
        return dateCreation.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getSexeLibelle() {
        return "M".equals(sexe) ? "Masculin" : "Féminin";
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public LocalDateTime getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDateTime dateNaissance) { this.dateNaissance = dateNaissance; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    @Override
    public String toString() {
        return String.format("👨‍🎓 %s (%s) - %s - %s", getNomComplet(), matricule, getSexeLibelle(), adresse);
    }
}