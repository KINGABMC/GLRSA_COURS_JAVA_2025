package com.ism.controllers;

import com.ism.models.Etudiant;
import com.ism.services.EtudiantService;
import com.ism.utils.ConsoleUtils;
import java.util.List;
import java.util.Scanner;

public class EtudiantController {
    private EtudiantService etudiantService = new EtudiantService();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTION DES ÉTUDIANTS");
            
            System.out.println("👨‍🎓 1. Ajouter un étudiant");
            System.out.println("📋 2. Lister les étudiants");
            System.out.println("🔍 3. Rechercher par nom");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    ajouterEtudiant();
                    break;
                case "2":
                    listerEtudiants("");
                    break;
                case "3":
                    rechercherParNom();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    private void ajouterEtudiant() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AJOUTER UN NOUVEL ÉTUDIANT");
        
        String nom = ConsoleUtils.readString("📝 Nom");
        String prenom = ConsoleUtils.readString("📝 Prénom");
        String adresse = ConsoleUtils.readString("🏠 Adresse");
        
        String sexe;
        while (true) {
            sexe = ConsoleUtils.readString("👤 Sexe (M/F)").toUpperCase();
            if ("M".equals(sexe) || "F".equals(sexe)) {
                break;
            }
            ConsoleUtils.printError("❌ Veuillez entrer M ou F");
        }
        
        Etudiant etudiant = new Etudiant(nom, prenom, adresse, sexe);
        etudiantService.addEtudiant(etudiant);
        
        ConsoleUtils.printSuccess("✅ Étudiant ajouté avec succès!");
        ConsoleUtils.printInfo("📋 Matricule généré: " + etudiant.getMatricule());
        ConsoleUtils.pause();
    }

    private void listerEtudiants(String nom) {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTE DES ÉTUDIANTS" + (nom.isEmpty() ? "" : " - Recherche: " + nom));
        
        List<Etudiant> etudiants = etudiantService.getEtudiants(nom);
        
        if (etudiants.isEmpty()) {
            ConsoleUtils.printWarning("Aucun étudiant trouvé");
        } else {
            String[] headers = {"Matricule", "Nom Complet", "Sexe", "Adresse", "Date Inscription"};
            String[][] data = new String[etudiants.size()][5];
            
            for (int i = 0; i < etudiants.size(); i++) {
                Etudiant etudiant = etudiants.get(i);
                data[i][0] = etudiant.getMatricule();
                data[i][1] = etudiant.getNomComplet();
                data[i][2] = etudiant.getSexeLibelle();
                data[i][3] = etudiant.getAdresse();
                data[i][4] = etudiant.getDateToString();
            }
            
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void rechercherParNom() {
        String nom = ConsoleUtils.readString("🔍 Entrez le nom ou prénom à rechercher");
        listerEtudiants(nom);
    }
}