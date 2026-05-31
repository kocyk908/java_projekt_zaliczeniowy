package com.university.techcorp.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;
    private List<Employee> team;
    private ProjectStatus status;

    public Project(String name, int requiredWork) {
        validateName(name);
        validateRequiredWork(requiredWork);

        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
        this.team = new ArrayList<>();
        this.status = ProjectStatus.PLANNED;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) { 
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        team.add(employee);
    }

    public void start() {
        if (status == ProjectStatus.PLANNED) {
            status = ProjectStatus.IN_PROGRESS;
        }
    }

    public void freeze() {
        if (this.status != ProjectStatus.IN_PROGRESS) {
            throw new IllegalStateException("Można zamrozić tylko projekt, który jest już w trakcie realizacji (IN_PROGRESS).");
        }
        this.status = ProjectStatus.FROZEN;
    }

    public void resume() {
        if (status == ProjectStatus.FROZEN) {
            status = ProjectStatus.IN_PROGRESS;
        }
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void workOneTurn() {
        if (status != ProjectStatus.IN_PROGRESS) {
            return;
        }
        for (Employee employee : team) {
            progress += employee.work();
        }

        if (progress >= requiredWork) {
            progress = requiredWork;
            status = ProjectStatus.FINISHED;
        }
    }

    public void reduceProgress(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Wartość redukcji nie może być ujemna.");
        }
        
        this.progress -= amount;
        
        if (this.progress < 0) {
            this.progress = 0;
        }
    }

    public boolean isFinished() {
        return status == ProjectStatus.FINISHED;
    }

    public int getCompletionPercentage() {
        return (progress * 100) / requiredWork;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWork() {
        return requiredWork;
    }

    public int getProgress() {
        return progress;
    }

    public List<Employee> getTeam() {
        return team;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be null or blank.");
        }
    }

    private void validateRequiredWork(int requiredWork) {
        if (requiredWork <= 0) {
            throw new IllegalArgumentException("Required work must be greater than 0.");
        }
    }
}