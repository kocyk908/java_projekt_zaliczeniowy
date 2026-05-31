package com.university.techcorp;

import com.university.techcorp.domain.*;
import com.university.techcorp.engine.GameEngine;
import com.university.techcorp.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {
        Company company = new Company("TechCorp", 350_000);

        Employee anna = new Developer("Anna", 9, 8_000);
        Employee jan = new Developer("Jan", 10, 6_000);
        Employee piotr = new Tester("Piotr", 6, 6_500);
        Employee ewa = new Manager("Ewa", 7, 9_000);

        // New employee types
        Employee tomek = new Intern("Tomek", 4, 3_000);
        Employee magda = new DataEngineer("Magda", 8, 7_000);
        
        company.hire(anna);
        company.hire(jan);
        company.hire(piotr);
        company.hire(ewa);
        company.hire(tomek);
        company.hire(magda);

        Project mobileApp = new Project("Mobile App", 120);
        Project website = new Project("Website", 40);

        company.addProject(mobileApp);
        company.addProject(website);
        
        company.assignEmployee(mobileApp, anna);
        company.assignEmployee(mobileApp, piotr);
        company.assignEmployee(mobileApp, ewa);
        company.assignEmployee(mobileApp, magda);
        company.assignEmployee(website, jan);
        company.assignEmployee(website, tomek);


        ConsoleUI ui = new ConsoleUI();
        GameEngine engine = new GameEngine(company, ui);

        engine.start();
    }
}