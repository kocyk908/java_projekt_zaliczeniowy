package com.university.techcorp.domain;

public class DataEngineer extends Employee {
    public DataEngineer(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return Math.max(1, (getSkill() / 2) + 1);
    }

    @Override
    public String getRoleName() {
        return "Data Engineer";
    }
}