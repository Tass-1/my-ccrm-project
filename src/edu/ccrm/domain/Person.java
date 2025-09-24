package edu.ccrm.domain;

public abstract class Person {
    protected int id;
    protected String fullName;
    protected String email;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public abstract String getProfileDetails();

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}