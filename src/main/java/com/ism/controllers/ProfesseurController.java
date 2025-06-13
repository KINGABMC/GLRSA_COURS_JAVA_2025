package com.ism.controllers;

import com.ism.utils.ConsoleUtils;
import java.util.Scanner;

public class ProfesseurController {
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTION DES PROFESSEURS");
            
            System.out.println("👨‍🏫 1. Ajouter un professeur");
            System.out.println("📋 2. Lister les professeurs");
            System.out.println("📚 3. Affecter modules");
            System.out.println("🏫 4. Affecter classes");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    ajouterProfesseur();
                    break;
                case "2":
                    listerProfesseurs();
                    break;
                case "3":
                    affecterModules();
                    break;
                case "4":
                    affecterClasses();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    public void showMesClasses() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("MES CLASSES");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    public void showMesModules() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("MES MODULES");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    public void showMesEtudiants() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("MES ÉTUDIANTS");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    private void ajouterProfesseur() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AJOUTER UN PROFESSEUR");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    private void listerProfesseurs() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTE DES PROFESSEURS");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    private void affecterModules() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AFFECTER DES MODULES");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }

    private void affecterClasses() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("AFFECTER DES CLASSES");
        ConsoleUtils.printInfo("🚧 Fonctionnalité en cours de développement");
        ConsoleUtils.pause();
    }
}