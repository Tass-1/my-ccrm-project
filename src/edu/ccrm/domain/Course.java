package edu.ccrm.domain;

public class Course {
    private final String code;
    private final String title;
    private final int credits;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
    }

    @Override
    public String toString() {
        return String.format("Course [Code=%s, Title=%s, Credits=%d]", code, title, credits);
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    public static class Builder {
        private final String code;
        private final String title;
        private int credits;

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}