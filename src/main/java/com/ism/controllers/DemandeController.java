package com.ism.controllers;

import com.ism.models.Demande;
import com.ism.models.Etudiant;
import com.ism.services.DemandeService;
import com.ism.services.EtudiantService;
import com.ism.utils.ConsoleUtils;
import com.ism.utils.SessionManager;
import java.util.List;
import java.util.Scanner;

public class DemandeController {
    private DemandeService demandeService = new DemandeService();
    private EtudiantService etudiantService = new EtudiantService();
    private Scanner scanner = new Scanner(System.in);

    public void showMenuRP() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTION DES DEMANDES (RP)");
            
            System.out.println("📋 1. Demandes en attente");
            System.out.println("✅ 2. Demandes traitées");
            System.out.println("📊 3. Toutes les demandes");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    listerDemandesEnAttente();
                    break;
                case "2":
                    listerDemandesTraitees();
                    break;
                case "3":
                    listerToutesDemandes();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    public void showMenuAttache() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("CONSULTATION DES DEMANDES (ATTACHÉ)");
            
            System.out.println("🔍 1. Rechercher par matricule");
            System.out.println("📋 2. Toutes les demandes");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    rechercherDemandesParMatricule();
                    break;
                case "2":
                    listerToutesDemandes();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    public void showMesDemandes() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("MES DEMANDES");
        
        // Pour l'instant, simulation avec un étudiant fictif
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.printInfo("📋 Ici s'afficheront vos demandes de suspension/annulation");
        ConsoleUtils.pause();
    }

    public void showFormDemande() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("NOUVELLE DEMANDE");
        
        System.out.println("📝 Type de demande:");
        System.out.println("1. 🔄 Suspension");
        System.out.println("2. ❌ Annulation");
        
        int choixType = ConsoleUtils.readInt("👉 Votre choix");
        String type = choixType == 1 ? "SUSPENSION" : "ANNULATION";
        
        String motif = ConsoleUtils.readString("📝 Motif de la demande");
        
        // Pour l'instant, simulation avec un étudiant fictif
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.printSuccess("✅ Demande de " + type.toLowerCase() + " enregistrée!");
        ConsoleUtils.printInfo("📋 Motif: " + motif);
        ConsoleUtils.pause();
    }

    private void listerDemandesEnAttente() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("DEMANDES EN ATTENTE");
        
        List<Demande> demandes = demandeService.getDemandesParEtat("EN_ATTENTE");
        afficherDemandes(demandes, true);
    }

    private void listerDemandesTraitees() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("DEMANDES TRAITÉES");
        
        List<Demande> demandes = demandeService.getDemandesTraitees();
        afficherDemandes(demandes, false);
    }

    private void listerToutesDemandes() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("TOUTES LES DEMANDES");
        
        List<Demande> demandes = demandeService.getAllDemandes();
        afficherDemandes(demandes, SessionManager.isRP());
    }

    private void rechercherDemandesParMatricule() {
        String matricule = ConsoleUtils.readString("🔍 Matricule de l'étudiant");
        Etudiant etudiant = etudiantService.getEtudiantByMatricule(matricule);
        
        if (etudiant == null) {
            ConsoleUtils.printError("❌ Étudiant non trouvé!");
            ConsoleUtils.pause();
            return;
        }
        
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("DEMANDES DE " + etudiant.getNomComplet());
        
        List<Demande> demandes = demandeService.getDemandesParEtudiant(etudiant.getId());
        afficherDemandes(demandes, false);
    }

    private void afficherDemandes(List<Demande> demandes, boolean peutTraiter) {
        if (demandes.isEmpty()) {
            ConsoleUtils.printWarning("Aucune demande trouvée");
        } else {
            String[] headers = {"ID", "Étudiant", "Type", "État", "Date Demande", "Motif"};
            String[][] data = new String[demandes.size()][6];
            
            for (int i = 0; i < demandes.size(); i++) {
                Demande demande = demandes.get(i);
                Etudiant etudiant = etudiantService.getEtudiantById(demande.getEtudiantId());
                
                data[i][0] = String.valueOf(demande.getId());
                data[i][1] = etudiant != null ? etudiant.getNomComplet() : "N/A";
                data[i][2] = demande.getTypeLibelle();
                data[i][3] = demande.getEtatLibelle();
                data[i][4] = demande.getDateDemandeToString();
                data[i][5] = demande.getMotif().length() > 30 ? 
                    demande.getMotif().substring(0, 30) + "..." : demande.getMotif();
            }
            
            ConsoleUtils.printTable(headers, data);
            
            if (peutTraiter && !demandes.isEmpty()) {
                System.out.println("\n⚙️ Actions disponibles:");
                System.out.println("1. ✅ Accepter une demande");
                System.out.println("2. ❌ Refuser une demande");
                System.out.println("0. 🔙 Retour");
                
                int choix = ConsoleUtils.readInt("👉 Votre choix");
                if (choix == 1 || choix == 2) {
                    traiterDemande(choix == 1);
                    return;
                }
            }
        }
        
        ConsoleUtils.pause();
    }

    private void traiterDemande(boolean accepter) {
        int demandeId = ConsoleUtils.readInt("📋 ID de la demande à traiter");
        String decision = accepter ? "ACCEPTEE" : "REFUSEE";
        
        if (demandeService.traiterDemande(demandeId, decision, SessionManager.getCurrentUser().getId())) {
            ConsoleUtils.printSuccess("✅ Demande " + (accepter ? "acceptée" : "refusée") + " avec succès!");
        } else {
            ConsoleUtils.printError("❌ Erreur lors du traitement de la demande!");
        }
        
        ConsoleUtils.pause();
    }
}