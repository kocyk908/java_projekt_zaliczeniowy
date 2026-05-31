package com.university.techcorp.events;

import com.university.techcorp.domain.Company;

public class InvestorFundingEvent implements GameEvent {

    public void apply(Company company) {
        double fundingAmount = 20000.0;
        System.out.println("\n[!] ZDARZENIE LOSOWE [!]");
        System.out.println("Pojawił się inwestor! Firma " + company.getName() + " otrzymuje dofinansowanie w wysokości " + fundingAmount + " PLN.");
        company.addCash(fundingAmount);
    }
}