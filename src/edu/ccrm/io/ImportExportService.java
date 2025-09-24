package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImportExportService {

    private static final String FILE_NAME = "ccrm_data.csv";

    public void exportData(List<Student> students, List<Course> courses) {
        List<String> lines = new ArrayList<>();
        
        for (Student s : students) {
            lines.add(String.format("STUDENT,%d,%s,%s,%s", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo()));
        }
        
        for (Course c : courses) {
            lines.add(String.format("COURSE,%s,%s,%d", c.getCode(), c.getTitle(), c.getCredits()));
        }

        try {
            Path path = Paths.get(FILE_NAME);
            Files.write(path, lines);
            System.out.println("Data successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public void importData(StudentService studentService, CourseService courseService) {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            System.out.println("No existing data file found. Starting fresh.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String type = parts[0];
                if ("STUDENT".equals(type)) {
                    // This is a simplified import. A real app would handle this more robustly.
                    String name = parts[2];
                    String email = parts[3];
                    String regNo = parts[4];
                    studentService.addStudent(name, email, regNo);
                } else if ("COURSE".equals(type)) {
                    String code = parts[1];
                    String title = parts[2];
                    int credits = Integer.parseInt(parts[3]);
                    Course course = new Course.Builder(code, title).credits(credits).build();
                    courseService.addCourse(course);
                }
            }
            System.out.println("Data successfully loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}