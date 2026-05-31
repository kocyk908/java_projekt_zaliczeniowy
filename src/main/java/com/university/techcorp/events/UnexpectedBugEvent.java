package com.university.techcorp.events;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Employee;
import com.university.techcorp.domain.Project;
import com.university.techcorp.domain.ProjectStatus;

public class UnexpectedBugEvent implements GameEvent {

    public void apply(Company company) {
        System.out.println("\n[!] ZDARZENIE LOSOWE [!]");
        System.out.println("Błąd w kodzie!");

        boolean anyProjectAffected = false;

        for (Project project : company.getProjects()) {
            if (project.getStatus() == ProjectStatus.IN_PROGRESS) {
                
                // 1. Sprawdzamy, czy w TYM KONKRETNYM PROJEKCIE jest Tester
                boolean hasTester = false;
                for (Employee employee : project.getTeam()) {
                    if (employee.getRoleName().equals("Tester")) {
                        hasTester = true;
                        break; // Znaleźliśmy testera w projekcie, przerywamy pętlę
                    }
                }

                // 2. Ustalamy karę dla tego konkretnego projektu
                int penalty;
                if (hasTester) {
                    penalty = 5;
                    System.out.println("- Projekt '" + project.getName() + "': Twój Tester wyłapał problem! Tracisz tylko " + penalty + " pkt postępu.");
                } else {
                    penalty = 10;
                    System.out.println("- Projekt '" + project.getName() + "': Brak Testera w zespole! Błąd rósł w ukryciu. Tracisz " + penalty + " pkt postępu.");
                }

                // 3. Aplikujemy karę
                project.reduceProgress(penalty);
                anyProjectAffected = true;
            }
        }
    }
}