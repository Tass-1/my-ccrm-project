package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.MaxCreditLimitExceededException;

public class EnrollmentService {
    
    private static final int MAX_CREDITS = 20;

    public void enrollStudent(Student student, Course course) throws MaxCreditLimitExceededException {
        int currentCredits = 0;
        for (Enrollment enrollment : student.getEnrollments()) {
            currentCredits += enrollment.getCourse().getCredits();
        }

        if (currentCredits + course.getCredits() > MAX_CREDITS) {
            throw new MaxCreditLimitExceededException(
                "Enrollment failed: Student cannot exceed " + MAX_CREDITS + " credits."
            );
        }
        
        Enrollment newEnrollment = new Enrollment(course);
        student.getEnrollments().add(newEnrollment);
    }

    public void assignGrade(Enrollment enrollment, Grade grade) {
        enrollment.setGrade(grade);
    }
}