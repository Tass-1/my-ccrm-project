package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private List<Enrollment> enrollments; // This line has changed

    public Student(int id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollments = new ArrayList<>(); // This line has changed
    }

    @Override
    public String getProfileDetails() {
        return String.format("Student [ID=%d, Name=%s, RegNo=%s]", id, fullName, regNo);
    }

    @Override
    public String toString() {
        return getProfileDetails();
    }

    public String getRegNo() { return regNo; }

    public List<Enrollment> getEnrollments() { // This method has changed
        return enrollments;
    }
}