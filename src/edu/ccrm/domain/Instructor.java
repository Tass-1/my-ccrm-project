package edu.ccrm.domain;

public class Instructor extends Person {
    private String department;

    public Instructor(int id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
    }

    @Override
    public String getProfileDetails() {
        return String.format("Instructor [ID=%d, Name=%s, Dept=%s]", id, fullName, department);
    }

    @Override
    public String toString() {
        return getProfileDetails();
    }

    public String getDepartment() {
        return department;
    }
}