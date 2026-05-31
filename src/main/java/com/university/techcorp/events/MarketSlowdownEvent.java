package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class MarketSlowdownEvent implements GameEvent {

    public void apply(Company company) {
        double slowdownImpact = 5000.0;
        System.out.println("\n[!] ZDARZENIE LOSOWE [!]");
        System.out.println("Spowolnienie rynku! Firma " + company.getName() + " traci " + slowdownImpact + " PLN z powodu spadku popytu.");
        company.reduceCash(slowdownImpact);
    }
}