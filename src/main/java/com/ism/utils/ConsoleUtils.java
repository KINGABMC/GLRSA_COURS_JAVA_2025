package com.ism.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        // Simule le nettoyage de l'écran
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void printHeader(String title) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║%80s║%n", "");
        System.out.printf("║%s%s%s║%n", 
            " ".repeat((80 - title.length()) / 2), 
            title, 
            " ".repeat((80 - title.length()) / 2 + (80 - title.length()) % 2));
        System.out.printf("║%80s║%n", "");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void printSeparator() {
        System.out.println("═".repeat(80));
    }

    public static void printSuccess(String message) {
        System.out.println("✅ " + message);
    }

    public static void printError(String message) {
        System.out.println("❌ " + message);
    }

    public static void printWarning(String message) {
        System.out.println("⚠️ " + message);
    }

    public static void printInfo(String message) {
        System.out.println("ℹ️ " + message);
    }

    public static void pause() {
        System.out.println("\n📌 Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    public static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printError("Veuillez entrer un nombre valide!");
            }
        }
    }

    public static void printTable(String[] headers, String[][] data) {
        // Calculer la largeur des colonnes
        int[] widths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
            for (String[] row : data) {
                if (i < row.length && row[i] != null) {
                    widths[i] = Math.max(widths[i], row[i].length());
                }
            }
            widths[i] += 2; // Padding
        }

        // Imprimer l'en-tête
        printTableSeparator(widths);
        printTableRow(headers, widths);
        printTableSeparator(widths);

        // Imprimer les données
        for (String[] row : data) {
            printTableRow(row, widths);
        }
        printTableSeparator(widths);
    }

    private static void printTableSeparator(int[] widths) {
        System.out.print("┌");
        for (int i = 0; i < widths.length; i++) {
            System.out.print("─".repeat(widths[i]));
            if (i < widths.length - 1) {
                System.out.print("┬");
            }
        }
        System.out.println("┐");
    }

    private static void printTableRow(String[] row, int[] widths) {
        System.out.print("│");
        for (int i = 0; i < widths.length; i++) {
            String cell = i < row.length && row[i] != null ? row[i] : "";
            System.out.printf(" %-" + (widths[i] - 1) + "s│", cell);
        }
        System.out.println();
    }
}