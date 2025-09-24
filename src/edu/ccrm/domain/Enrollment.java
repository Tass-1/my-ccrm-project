package edu.ccrm.domain;

public class Enrollment {
    private Course course;
    private Grade grade; 

    public Enrollment(Course course) {
        this.course = course;
        this.grade = null;
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String gradeString = (grade == null) ? "Not Graded" : grade.toString();
        return String.format("Enrollment[Course: %s, Grade: %s]", course.getTitle(), gradeString);
    }
}