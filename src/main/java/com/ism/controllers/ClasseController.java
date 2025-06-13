package com.ism.controllers;

import com.ism.models.Classe;
import com.ism.services.ClasseService;
import com.ism.utils.ConsoleUtils;
import java.util.List;
import java.util.Scanner;

public class ClasseController {
    private ClasseService classeService = new ClasseService();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            ConsoleUtils.clearScreen();
            ConsoleUtils.printHeader("GESTION DES CLASSES");
            
            System.out.println("🏫 1. Créer une classe");
            System.out.println("📋 2. Lister les classes");
            System.out.println("🔍 3. Rechercher par filière");
            System.out.println("🔙 0. Retour");
            
            ConsoleUtils.printSeparator();
            System.out.print("👉 Votre choix: ");
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    creerClasse();
                    break;
                case "2":
                    listerClasses("");
                    break;
                case "3":
                    rechercherParFiliere();
                    break;
                case "0":
                    return;
                default:
                    ConsoleUtils.printError("❌ Choix invalide!");
                    ConsoleUtils.pause();
            }
        }
    }

    private void creerClasse() {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("CRÉER UNE NOUVELLE CLASSE");
        
        String libelle = ConsoleUtils.readString("📝 Libellé de la classe");
        String filiere = ConsoleUtils.readString("🎓 Filière");
        String niveau = ConsoleUtils.readString("📊 Niveau");
        
        Classe classe = new Classe(libelle, filiere, niveau);
        classeService.addClasse(classe);
        
        ConsoleUtils.printSuccess("✅ Classe créée avec succès!");
        ConsoleUtils.pause();
    }

    private void listerClasses(String filiere) {
        ConsoleUtils.clearScreen();
        ConsoleUtils.printHeader("LISTE DES CLASSES" + (filiere.isEmpty() ? "" : " - Filière: " + filiere));
        
        List<Classe> classes = classeService.getClasses(filiere);
        
        if (classes.isEmpty()) {
            ConsoleUtils.printWarning("Aucune classe trouvée");
        } else {
            String[] headers = {"ID", "Libellé", "Filière", "Niveau", "Date Création"};
            String[][] data = new String[classes.size()][5];
            
            for (int i = 0; i < classes.size(); i++) {
                Classe classe = classes.get(i);
                data[i][0] = String.valueOf(classe.getId());
                data[i][1] = classe.getLibelle();
                data[i][2] = classe.getFiliere();
                data[i][3] = classe.getNiveau();
                data[i][4] = classe.getDateToString();
            }
            
            ConsoleUtils.printTable(headers, data);
        }
        
        ConsoleUtils.pause();
    }

    private void rechercherParFiliere() {
        String filiere = ConsoleUtils.readString("🔍 Entrez la filière à rechercher");
        listerClasses(filiere);
    }
}