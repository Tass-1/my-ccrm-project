package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;

public class TranscriptService {

    public double calculateGPA(Student student) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Enrollment enrollment : student.getEnrollments()) {
            if (enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                double gradePoint = enrollment.getGrade().getGradePoint();
                
                totalPoints += gradePoint * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) {
            return 0.0; // Avoid division by zero
        }

        return totalPoints / totalCredits;
    }
}