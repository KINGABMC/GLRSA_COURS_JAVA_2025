package com.ism.controllers;

import com.ism.models.Classe;
import com.ism.models.Etudiant;
import com.ism.models.Inscription;
import com.ism.services.ClasseService;
import com.ism.services.EtudiantService;
import com.ism.services.InscriptionService;
import com.ism.utils.ConsoleUtils;
import java.util.List;
import java.util.Scanner;

public class InscriptionController {
    private InscriptionService inscriptionService = new InscriptionService();
    private EtudiantService etudiantService = new EtudiantService();
    private ClasseService classeService = new ClasseService();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTION DES INSCRIPTIONS");
            
            System.out.println("📝 1. Inscrire un étudiant");
            System.out.println("👥 2. Étudiants par classe");
            System.out.println("📋 3. Toutes les inscriptions");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    inscrireEtudiant();
                    break;
                case "2":
                    consulterEtudiantsClasse();
                    break;
                case "3":
                    listerInscriptions();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    private void inscrireEtudiant() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("INSCRIPTION D'UN ÉTUDIANT");
        
        // Sélection de l'étudiant
        String matricule = ConsoleUtils.readString("📋 Matricule de l'étudiant");
        Etudiant etudiant = etudiantService.getEtudiantByMatricule(matricule);
        
        if (etudiant == null) {
            ConsoleUtils.printError("❌ Étudiant non trouvé avec ce matricule!");
            ConsoleUtils.pause();
            return;
        }
        
        ConsoleUtils.printInfo("✅ Étudiant trouvé: " + etudiant.getNomComplet());
        
        // Affichage des classes disponibles
        List<Classe> classes = classeService.getClasses("");
        if (classes.isEmpty()) {
            ConsoleUtils.printError("❌ Aucune classe disponible!");
            ConsoleUtils.pause();
            return;
        }
        
        System.out.println("\n🏫 Classes disponibles:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i));
        }
        
        int choixClasse = ConsoleUtils.readInt("👉 Choisissez une classe (numéro)") - 1;
        if (choixClasse < 0 || choixClasse >= classes.size()) {
            ConsoleUtils.printError("❌ Choix invalide!");
            ConsoleUtils.pause();
            return;
        }
        
        Classe classe = classes.get(choixClasse);
        String anneeScolaire = ConsoleUtils.readString("📅 Année scolaire (ex: 2024-2025)");
        
        // Vérification si déjà inscrit
        if (inscriptionService.isEtudiantInscrit(etudiant.getId(), anneeScolaire)) {
            ConsoleUtils.printError("❌ Cet étudiant est déjà inscrit pour cette année!");
            ConsoleUtils.pause();
            return;
        }
        
        // Inscription
        Inscription inscription = new Inscription(etudiant.getId(), classe.getId(), anneeScolaire);
        inscriptionService.inscrireEtudiant(inscription);
        
        ConsoleUtils.printSuccess("✅ Inscription réussie!");
        ConsoleUtils.printInfo("📋 Étudiant: " + etudiant.getNomComplet());
        ConsoleUtils.printInfo("🏫 Classe: " + classe.getLibelle());
        ConsoleUtils.printInfo("📅 Année: " + anneeScolaire);
        ConsoleUtils.pause();
    }

    private void consulterEtudiantsClasse() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ÉTUDIANTS PAR CLASSE");
        
        // Affichage des classes
        List<Classe> classes = classeService.getClasses("");
        if (classes.isEmpty()) {
            ConsoleUtils.printError("❌ Aucune classe disponible!");
            ConsoleUtils.pause();
            return;
        }
        
        System.out.println("🏫 Classes disponibles:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i));
        }
        
        int choixClasse = ConsoleUtils.readInt("👉 Choisissez une classe (numéro)") - 1;
        if (choixClasse < 0 || choixClasse >= classes.size()) {
            ConsoleUtils.printError("❌ Choix invalide!");
            ConsoleUtils.pause();
            return;
        }
        
        Classe classe = classes.get(choixClasse);
        String anneeScolaire = ConsoleUtils.readString("📅 Année scolaire (ex: 2024-2025)");
        
        List<Etudiant> etudiants = inscriptionService.getEtudiantsParClasse(classe.getId(), anneeScolaire);
        
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("ÉTUDIANTS - " + classe.getLibelle() + " (" + anneeScolaire + ")");
        
        if (etudiants.isEmpty()) {
            ConsoleUtils.printWarning("Aucun étudiant inscrit dans cette classe pour cette année");
        } else {
            String[] headers = {"Matricule", "Nom Complet", "Sexe", "Adresse"};
            String[][] data = new String[etudiants.size()][4];
            
            for (int i = 0; i < etudiants.size(); i++) {
                Etudiant etudiant = etudiants.get(i);
                data[i][0] = etudiant.getMatricule();
                data[i][1] = etudiant.getNomComplet();
                data[i][2] = etudiant.getSexeLibelle();
                data[i][3] = etudiant.getAdresse();
            }
            
            ConsoleUtils.printTable(headers, data);
            
            // Statistiques rapides
            long masculins = etudiants.stream().filter(e -> "M".equals(e.getSexe())).count();
            long feminins = etudiants.stream().filter(e -> "F".equals(e.getSexe())).count();
            
            System.out.println("\n📊 Statistiques:");
            System.out.println("👨 Masculins: " + masculins);
            System.out.println("👩 Féminins: " + feminins);
            System.out.println("👥 Total: " + etudiants.size());
        }
        
        ConsoleUtils.pause();
    }

    private void listerInscriptions() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("TOUTES LES INSCRIPTIONS");
        
        List<Inscription> inscriptions = inscriptionService.getAllInscriptions();
        
        if (inscriptions.isEmpty()) {
            ConsoleUtils.printWarning("Aucune inscription trouvée");
        } else {
            String[] headers = {"ID", "Étudiant", "Classe", "Année", "Statut", "Date"};
            String[][] data = new String[inscriptions.size()][6];
            
            for (int i = 0; i < inscriptions.size(); i++) {
                Inscription inscription = inscriptions.get(i);
                Etudiant etudiant = etudiantService.getEtudiantById(inscription.getEtudiantId());
                Classe classe = classeService.getClasseById(inscription.getClasseId());
                
                data[i][0] = String.valueOf(inscription.getId());
                data[i][1] = etudiant != null ? etudiant.getNomComplet() : "N/A";
                data[i][2] = classe != null ? classe.getLibelle() : "N/A";
                data[i][3] = inscription.getAnneeScolaire();
                data[i][4] = inscription.getStatutLibelle();
                data[i][5] = inscription.getDateToString();
            }
            
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }
}