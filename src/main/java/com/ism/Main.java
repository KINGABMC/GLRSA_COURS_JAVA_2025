package com.ism;

import com.ism.controllers.AuthController;
import com.ism.controllers.DashboardController;
import com.ism.controllers.ClasseController;
import com.ism.controllers.EtudiantController;
import com.ism.controllers.InscriptionController;
import com.ism.controllers.ProfesseurController;
import com.ism.controllers.DemandeController;
import com.ism.models.User;
import com.ism.utils.ConsoleUtils;
import com.ism.utils.SessionManager;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthController authController = new AuthController();
    private static DashboardController dashboardController = new DashboardController();
    private static ClasseController classeController = new ClasseController();
    private static EtudiantController etudiantController = new EtudiantController();
    private static InscriptionController inscriptionController = new InscriptionController();
    private static ProfesseurController professeurController = new ProfesseurController();
    private static DemandeController demandeController = new DemandeController();

    public static void main(String[] args) {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("SYSTÈME DE GESTION SCOLAIRE ISM");
        
        // Boucle principale de l'application
        while (true) {
            if (!SessionManager.isLoggedIn()) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        ConsoleUtils.printSeparator();
        System.out.println("🔐 CONNEXION");
        ConsoleUtils.printSeparator();
        
        System.out.print("📧 Email: ");
        String email = scanner.nextLine();
        
        System.out.print("🔑 Mot de passe: ");
        String password = scanner.nextLine();
        
        if (authController.login(email, password)) {
            User currentUser = SessionManager.getCurrentUser();
            ConsoleUtils.printSuccess("✅ Connexion réussie! Bienvenue " + currentUser.getNomComplet());
            ConsoleUtils.pause();
        } else {
            ConsoleUtils.printError("❌ Email ou mot de passe incorrect!");
            ConsoleUtils.pause();
        }
    }

    private static void showMainMenu() {
        ConsoleUtils.clearScreen();
        User currentUser = SessionManager.getCurrentUser();
        
        ConsoleUtils.printHeader("TABLEAU DE BORD - " + currentUser.getNomComplet() + " (" + currentUser.getRole() + ")");
        
        System.out.println("📊 1. Statistiques");
        
        // Menu selon le rôle
        switch (currentUser.getRole()) {
            case "RP":
                showRPMenu();
                break;
            case "ATTACHE":
                showAttacheMenu();
                break;
            case "PROFESSEUR":
                showProfesseurMenu();
                break;
            case "ETUDIANT":
                showEtudiantMenu();
                break;
        }
        
        System.out.println("🚪 0. Déconnexion");
        ConsoleUtils.printSeparator();
        
        System.out.print("👉 Votre choix: ");
        String choix = scanner.nextLine();
        
        handleMenuChoice(choix, currentUser.getRole());
    }

    private static void showRPMenu() {
        System.out.println("🏫 2. Gestion des Classes");
        System.out.println("👨‍🏫 3. Gestion des Professeurs");
        System.out.println("📋 4. Gestion des Demandes");
        System.out.println("📊 5. Statistiques Avancées");
    }

    private static void showAttacheMenu() {
        System.out.println("👥 2. Gestion des Étudiants");
        System.out.println("📝 3. Inscriptions");
        System.out.println("📋 4. Consulter les Demandes");
    }

    private static void showProfesseurMenu() {
        System.out.println("📚 2. Mes Classes");
        System.out.println("📖 3. Mes Modules");
        System.out.println("👥 4. Mes Étudiants");
    }

    private static void showEtudiantMenu() {
        System.out.println("📝 2. Mes Demandes");
        System.out.println("➕ 3. Nouvelle Demande");
    }

    private static void handleMenuChoice(String choix, String role) {
        switch (choix) {
            case "1":
                dashboardController.showStatistiques();
                break;
            case "2":
                handleSecondChoice(role);
                break;
            case "3":
                handleThirdChoice(role);
                break;
            case "4":
                handleFourthChoice(role);
                break;
            case "5":
                if ("RP".equals(role)) {
                    dashboardController.showStatistiquesAvancees();
                }
                break;
            case "0":
                authController.logout();
                ConsoleUtils.printSuccess("✅ Déconnexion réussie!");
                ConsoleUtils.pause();
                break;
            default:
                ConsoleUtils.printError("❌ Choix invalide!");
                ConsoleUtils.pause();
        }
    }

    private static void handleSecondChoice(String role) {
        switch (role) {
            case "RP":
                classeController.showMenu();
                break;
            case "ATTACHE":
                etudiantController.showMenu();
                break;
            case "PROFESSEUR":
                professeurController.showMesClasses();
                break;
            case "ETUDIANT":
                demandeController.showMesDemandes();
                break;
        }
    }

    private static void handleThirdChoice(String role) {
        switch (role) {
            case "RP":
                professeurController.showMenu();
                break;
            case "ATTACHE":
                inscriptionController.showMenu();
                break;
            case "PROFESSEUR":
                professeurController.showMesModules();
                break;
            case "ETUDIANT":
                demandeController.showFormDemande();
                break;
        }
    }

    private static void handleFourthChoice(String role) {
        switch (role) {
            case "RP":
                demandeController.showMenuRP();
                break;
            case "ATTACHE":
                demandeController.showMenuAttache();
                break;
            case "PROFESSEUR":
                professeurController.showMesEtudiants();
                break;
        }
    }
}