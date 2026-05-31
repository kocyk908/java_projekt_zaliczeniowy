package com.university.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double cash;
    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name, double cash) {
        validateName(name);
        validateCash(cash);

        this.name = name;
        this.cash = cash;
        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        employees.add(employee);
    }

    public void addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        projects.add(project);
    }

    /**
     * Assigns an employee to a project within this company.
     *
     * PRECONDITIONS (must be true BEFORE calling this method):
     * - project must not be null
     * - employee must not be null
     * - project must belong to this company (be in the projects list)
     * - employee must work for this company (be in the employees list)
     * - employee must NOT already be assigned to the project
     * 
     * POSTCONDITIONS (guaranteed to be true AFTER successful execution):
     * - employee is added to the project's team
     * - project's team size increases by 1
     * - employee's assignment remains valid for the rest of program execution
     * - no other state is modified
     */
    public void assignEmployee(Project project, Employee employee) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if (!projects.contains(project)) {
            throw new IllegalArgumentException("Project does not belong to this company.");
        }
        if (!employees.contains(employee)) {
            throw new IllegalArgumentException("Employee does not work for this company.");
        }
        for (Project p : projects) {
            if (p.getTeam().contains(employee)) {
                throw new IllegalStateException("Pracownik " + employee.getName() + " jest już zajęty pracą w projekcie " + p.getName() + "!");
            }
        }
        project.addEmployee(employee);
    }

    public void reduceCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota do odjęcia nie może być ujemna.");
        }
        
        if (this.cash - amount < 0) {
            throw new IllegalStateException("Firma " + this.name + " zbankrutowała! Brak środków na wypłaty.");
        }
        
        this.cash -= amount;
    }

    public void addCash(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota do dodania nie może być ujemna.");
        }
        this.cash += amount;
    }


    public void paySalaries() {
        double totalSalaries = 0;
        
        for (Project project : projects) {
            // Płacimy tylko zespołom z aktywnych projektów
            if (project.getStatus() == ProjectStatus.IN_PROGRESS) {
                
                for (Employee emp : project.getTeam()) {
                    totalSalaries += emp.getSalary();
                }
                
            }
        }
        
        // Odejmujemy sumę od budżetu firmy
        reduceCash(totalSalaries);
        
        System.out.println("Wypłacono pensje w łącznej kwocie: " + totalSalaries + " PLN.");
    }

    public void showStatus() {
        System.out.println("=== COMPANY STATUS ===");
        System.out.println("Name: " + name);
        System.out.println("Cash: " + cash);
        System.out.println("Employees: " + employees.size());
        System.out.println("Projects: " + projects.size());
        System.out.println();

        if (employees.isEmpty()) {
            System.out.println("No employees hired yet.");
        } else {
            System.out.println("Employees:");
            for (Employee employee : employees) {
                System.out.println(
                    "- " + employee.getRoleName()
                    + " | name: " + employee.getName()
                    + " | skill: " + employee.getSkill()
                    + " | salary: " + employee.getSalary()
                );
            }
        }

        System.out.println();

        if (projects.isEmpty()) {
            System.out.println("No active projects.");
        } else {
            System.out.println("Projects:");
            for (Project project : projects) {
                System.out.println(
                    "- " + project.getName()
                    + " | progress: " + project.getProgress()
                    + "/" + project.getRequiredWork()
                    + " | completion: " + project.getCompletionPercentage() + "%"
                    + " | finished: " + project.isFinished()
                );
            }
        }

        System.out.println("========================");
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be null or blank.");
        }
    }

    private void validateCash(double cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("Company cash cannot be negative.");
        }
    }
}