package com.ism.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Module {
    private int id;
    private String nom;
    private String code;
    private int coefficient;
    private LocalDateTime dateCreation;

    // Constructeurs
    public Module() {
        this.dateCreation = LocalDateTime.now();
    }

    public Module(String nom, String code, int coefficient) {
        this.nom = nom;
        this.code = code;
        this.coefficient = coefficient;
        this.dateCreation = LocalDateTime.now();
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getCoefficient() { return coefficient; }
    public void setCoefficient(int coefficient) { this.coefficient = coefficient; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    @Override
    public String toString() {
        return String.format("📚 %s (%s) - Coeff: %d", nom, code, coefficient);
    }
}