package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.TranscriptService;
import java.util.List;
import java.util.Scanner;

public class Cli {
    private final Scanner scanner = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final ImportExportService importExportService = new ImportExportService();
    private final TranscriptService transcriptService = new TranscriptService();

    public void start() {
        importExportService.importData(studentService, courseService);
        
        boolean running = true;
        while (running) {
            System.out.println("\n--- CCRM Menu ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("3. Add New Course");
            System.out.println("4. List All Courses");
            System.out.println("5. Enroll Student in Course");
            System.out.println("6. Assign Grade to Student");
            System.out.println("7. View Student Transcript");
            System.out.println("9. Save and Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addStudent(); break;
                case "2": listStudents(); break;
                case "3": addCourse(); break;
                case "4": listCourses(); break;
                case "5": enrollStudent(); break;
                case "6": assignGrade(); break;
                case "7": viewTranscript(); break;
                case "9":
                    importExportService.exportData(studentService.getAllStudents(), courseService.getAllCourses());
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void viewTranscript() {
        System.out.println("\n--- View Student Transcript ---");
        System.out.print("Enter student's registration number: ");
        String regNo = scanner.nextLine();
        
        Student student = studentService.findStudentByRegNo(regNo);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }

        System.out.println("\nTranscript for: " + student.getProfileDetails());
        System.out.println("------------------------------------");
        
        List<Enrollment> enrollments = student.getEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("This student has no course enrollments.");
        } else {
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        }

        double gpa = transcriptService.calculateGPA(student);
        System.out.println("------------------------------------");
        System.out.printf("Cumulative GPA: %.2f\n", gpa);
    }

    private void addStudent() {
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine();
        studentService.addStudent(name, email, regNo);
        System.out.println("Student added successfully.");
    }

    private void listStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> allStudents = studentService.getAllStudents();
        if (allStudents.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : allStudents) {
                System.out.println(student);
            }
        }
    }

    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter course code (e.g., CS101): ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());
        Course newCourse = new Course.Builder(code, title).credits(credits).build();
        courseService.addCourse(newCourse);
        System.out.println("Course added successfully.");
    }

    private void listCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> allCourses = courseService.getAllCourses();
        if (allCourses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : allCourses) {
                System.out.println(course);
            }
        }
    }

    private void enrollStudent() {
        System.out.println("\n--- Enroll Student in Course ---");
        System.out.print("Enter student's registration number: ");
        String regNo = scanner.nextLine();
        
        Student student = studentService.findStudentByRegNo(regNo);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }

        System.out.print("Enter course code to enroll in: ");
        String courseCode = scanner.nextLine();

        Course course = courseService.findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Error: Course not found.");
            return;
        }

        try {
            enrollmentService.enrollStudent(student, course);
            System.out.println("Student '" + student.getFullName() + "' successfully enrolled in '" + course.getTitle() + "'.");
        } catch (MaxCreditLimitExceededException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void assignGrade() {
        System.out.println("\n--- Assign Grade ---");
        System.out.print("Enter student's registration number: ");
        String regNo = scanner.nextLine();
        Student student = studentService.findStudentByRegNo(regNo);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        List<Enrollment> enrollments = student.getEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("This student is not enrolled in any courses.");
            return;
        }
        System.out.println("Select a course to grade:");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, enrollments.get(i));
        }
        System.out.print("Enter choice number: ");
        int courseChoice = Integer.parseInt(scanner.nextLine());
        if (courseChoice < 1 || courseChoice > enrollments.size()) {
            System.out.println("Invalid course selection.");
            return;
        }
        Enrollment selectedEnrollment = enrollments.get(courseChoice - 1);
        System.out.print("Enter Grade (S, A, B, C, D, E, F): ");
        String gradeInput = scanner.nextLine().toUpperCase();
        try {
            Grade grade = Grade.valueOf(gradeInput);
            enrollmentService.assignGrade(selectedEnrollment, grade);
            System.out.println("Grade assigned successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade. Please use one of S, A, B, C, D, E, F.");
        }
    }
}