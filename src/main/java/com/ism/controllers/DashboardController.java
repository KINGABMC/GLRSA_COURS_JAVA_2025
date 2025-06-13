package com.ism.controllers;

import com.ism.services.InscriptionService;
import com.ism.utils.ConsoleUtils;
import java.util.Map;
import java.util.Scanner;

public class DashboardController {
    private InscriptionService inscriptionService = new InscriptionService();
    private Scanner scanner = new Scanner(System.in);

    public void showStatistiques() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("STATISTIQUES GÉNÉRALES");
        
        Map<String, Object> stats = inscriptionService.getStatistiques();
        
        System.out.println("📊 RÉSUMÉ GÉNÉRAL");
        ConsoleUtils.printSeparator();
        System.out.println("👥 Total Étudiants: " + stats.get("totalEtudiants"));
        System.out.println("🏫 Total Classes: " + stats.get("totalClasses"));
        System.out.println("👨‍🏫 Total Professeurs: " + stats.get("totalProfesseurs"));
        System.out.println("📚 Total Modules: " + stats.get("totalModules"));
        
        ConsoleUtils.pause();
    }

    public void showStatistiquesAvancees() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("STATISTIQUES AVANCÉES");
        
        System.out.println("📈 1. Effectif par année scolaire");
        System.out.println("👫 2. Répartition par sexe et année");
        System.out.println("🏫 3. Effectif par classe");
        System.out.println("👥 4. Répartition par sexe et classe");
        System.out.println("⚠️ 5. Suspensions et annulations");
        System.out.println("🔙 0. Retour");
        
        ConsoleUtils.printSeparator();
        System.out.print("👉 Votre choix: ");
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "1":
                showEffectifParAnnee();
                break;
            case "2":
                showRepartitionSexeAnnee();
                break;
            case "3":
                showEffectifParClasse();
                break;
            case "4":
                showRepartitionSexeClasse();
                break;
            case "5":
                showSuspensionsAnnulations();
                break;
            case "0":
                return;
            default:
                ConsoleUtils.printError("❌ Choix invalide!");
                ConsoleUtils.pause();
                showStatistiquesAvancees();
        }
    }

    private void showEffectifParAnnee() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("EFFECTIF PAR ANNÉE SCOLAIRE");
        
        Map<String, Integer> effectifs = inscriptionService.getEffectifParAnnee();
        
        if (effectifs.isEmpty()) {
            ConsoleUtils.printWarning("Aucune donnée disponible");
        } else {
            String[] headers = {"Année Scolaire", "Effectif"};
            String[][] data = new String[effectifs.size()][2];
            int i = 0;
            for (Map.Entry<String, Integer> entry : effectifs.entrySet()) {
                data[i][0] = entry.getKey();
                data[i][1] = entry.getValue().toString();
                i++;
            }
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void showRepartitionSexeAnnee() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("RÉPARTITION PAR SEXE ET ANNÉE");
        
        Map<String, Map<String, Integer>> repartition = inscriptionService.getRepartitionSexeAnnee();
        
        if (repartition.isEmpty()) {
            ConsoleUtils.printWarning("Aucune donnée disponible");
        } else {
            String[] headers = {"Année Scolaire", "Masculin", "Féminin", "Total"};
            String[][] data = new String[repartition.size()][4];
            int i = 0;
            for (Map.Entry<String, Map<String, Integer>> entry : repartition.entrySet()) {
                String annee = entry.getKey();
                Map<String, Integer> sexes = entry.getValue();
                int masculin = sexes.getOrDefault("M", 0);
                int feminin = sexes.getOrDefault("F", 0);
                
                data[i][0] = annee;
                data[i][1] = String.valueOf(masculin);
                data[i][2] = String.valueOf(feminin);
                data[i][3] = String.valueOf(masculin + feminin);
                i++;
            }
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void showEffectifParClasse() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("EFFECTIF PAR CLASSE");
        
        Map<String, Integer> effectifs = inscriptionService.getEffectifParClasse();
        
        if (effectifs.isEmpty()) {
            ConsoleUtils.printWarning("Aucune donnée disponible");
        } else {
            String[] headers = {"Classe", "Effectif"};
            String[][] data = new String[effectifs.size()][2];
            int i = 0;
            for (Map.Entry<String, Integer> entry : effectifs.entrySet()) {
                data[i][0] = entry.getKey();
                data[i][1] = entry.getValue().toString();
                i++;
            }
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void showRepartitionSexeClasse() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("RÉPARTITION PAR SEXE ET CLASSE");
        
        Map<String, Map<String, Integer>> repartition = inscriptionService.getRepartitionSexeClasse();
        
        if (repartition.isEmpty()) {
            ConsoleUtils.printWarning("Aucune donnée disponible");
        } else {
            String[] headers = {"Classe", "Masculin", "Féminin", "Total"};
            String[][] data = new String[repartition.size()][4];
            int i = 0;
            for (Map.Entry<String, Map<String, Integer>> entry : repartition.entrySet()) {
                String classe = entry.getKey();
                Map<String, Integer> sexes = entry.getValue();
                int masculin = sexes.getOrDefault("M", 0);
                int feminin = sexes.getOrDefault("F", 0);
                
                data[i][0] = classe;
                data[i][1] = String.valueOf(masculin);
                data[i][2] = String.valueOf(feminin);
                data[i][3] = String.valueOf(masculin + feminin);
                i++;
            }
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void showSuspensionsAnnulations() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("SUSPENSIONS ET ANNULATIONS PAR ANNÉE");
        
        Map<String, Map<String, Integer>> stats = inscriptionService.getSuspensionsAnnulationsParAnnee();
        
        if (stats.isEmpty()) {
            ConsoleUtils.printWarning("Aucune donnée disponible");
        } else {
            String[] headers = {"Année Scolaire", "Suspensions", "Annulations", "Total"};
            String[][] data = new String[stats.size()][4];
            int i = 0;
            for (Map.Entry<String, Map<String, Integer>> entry : stats.entrySet()) {
                String annee = entry.getKey();
                Map<String, Integer> types = entry.getValue();
                int suspensions = types.getOrDefault("SUSPENDUE", 0);
                int annulations = types.getOrDefault("ANNULEE", 0);
                
                data[i][0] = annee;
                data[i][1] = String.valueOf(suspensions);
                data[i][2] = String.valueOf(annulations);
                data[i][3] = String.valueOf(suspensions + annulations);
                i++;
            }
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }
}