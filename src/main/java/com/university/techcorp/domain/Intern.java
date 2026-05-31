package com.university.techcorp.domain;

public class Intern extends Employee {
    public Intern(String name, int skill, double salary) {
        super(name, skill, salary);
    }

    @Override
    public int work() {
        return Math.max(1, getSkill() / 4);
    }


    @Override
    public String getRoleName() {
        return "Intern";
    }
}