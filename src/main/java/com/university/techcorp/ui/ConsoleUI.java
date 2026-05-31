package com.university.techcorp.ui;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Project;
import java.util.Scanner;

public class ConsoleUI {

    // KODY KOLORÓW ANSI
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    private final Scanner scanner = new Scanner(System.in);

    public void showTurnHeader(int turn) {
        System.out.println(ANSI_CYAN + "\n========================" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_RED + "       TURA " + turn + ANSI_RESET);
        System.out.println(ANSI_CYAN + "========================" + ANSI_RESET);
    }

    public void showMainMenu() {
        System.out.println("\nWybierz akcję:");
        System.out.println("1. Status firmy");
        System.out.println("2. Rozpocznij zaplanowane projekty");
        System.out.println("3. Pracuj nad projektami");
        System.out.println("4. Zamroź/Odmróź projekt");
        System.out.println("5. Wyjdź z gry");
    }

    public int readMenuChoice() {
        System.out.print("\nTwój wybór: ");
        if (!scanner.hasNextInt()) {
            scanner.nextLine(); // Wyczyszczenie błędnego wpisu
            return -1;
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii
        return choice;
    }

    public void showCompanyStatus(Company company) {
        System.out.println(ANSI_GREEN + "\n--- STATUS FIRMY ---" + ANSI_RESET);
        System.out.println("Nazwa: " + company.getName());
        System.out.println("Budżet: " + ANSI_YELLOW + company.getCash() + " PLN" + ANSI_RESET);
        System.out.println("Projekty:");
        
        if (company.getProjects().isEmpty()) {
            System.out.println("  Brak projektów.");
        } else {
            for (Project project : company.getProjects()) {
                System.out.printf("- %-15s | Status: %-10s | Postęp: %d/%d%n",
                        project.getName(),
                        project.getStatus(),
                        project.getProgress(),
                        project.getRequiredWork());
            }
        }
        System.out.println(ANSI_GREEN + "--------------------" + ANSI_RESET);
    }

    public void showMessage(String message) {
        System.out.println(">> " + message);
    }
}