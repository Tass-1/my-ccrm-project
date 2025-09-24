package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public void addStudent(String fullName, String email, String regNo) {
        Student student = new Student(nextId, fullName, email, regNo);
        students.add(student);
        nextId++;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentByRegNo(String regNo) {
        for (Student student : students) {
            if (student.getRegNo().equalsIgnoreCase(regNo)) {
                return student;
            }
        }
        return null;
    }
}