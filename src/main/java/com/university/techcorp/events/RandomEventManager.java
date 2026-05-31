package com.university.techcorp.events;

import com.university.techcorp.domain.Company;
import com.university.techcorp.domain.Project;
import com.university.techcorp.domain.ProjectStatus;
import java.util.Random;

public class RandomEventManager {

    public void triggerRandomEvent(Company company) {
        // 1. Sprawdzamy, czy gracz rozpoczął jakikolwiek projekt
        boolean anyProjectStarted = false;
        for (Project p : company.getProjects()) {
            if (p.getStatus() != ProjectStatus.PLANNED) {
                anyProjectStarted = true;
                break;
            }
        }
        
        // 2. Jeśli żaden projekt nie wystartował, wychodzimy (okres ochronny)
        if (!anyProjectStarted) {
            return; 
        }

        // 3. Właściwe losowanie
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 15) { 
            new UnexpectedBugEvent().apply(company);
        } else if (chance >= 15 && chance < 25) { 
            new InvestorFundingEvent().apply(company);
        } else if (chance >= 25 && chance < 35) {
            new MarketSlowdownEvent().apply(company);
        }
    }
}