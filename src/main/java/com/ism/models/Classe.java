package com.ism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classe {
    private int id;
    private String libelle;
    private String filiere;
    private String niveau;
    private LocalDateTime dateCreation;

    // Constructeurs
    public Classe() {
        this.dateCreation = LocalDateTime.now();
    }

    public Classe(String libelle, String filiere, String niveau) {
        this.libelle = libelle;
        this.filiere = filiere;
        this.niveau = niveau;
        this.dateCreation = LocalDateTime.now();
    }

    // Méthodes utilitaires
    public String getDateToString() {
        return dateCreation.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    @Override
    public String toString() {
        return String.format("🏫 %s - %s (%s)", libelle, filiere, niveau);
    }
}