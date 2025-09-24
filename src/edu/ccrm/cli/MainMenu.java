package edu.ccrm.cli;

import edu.ccrm.service.StudentService;

import java.util.Scanner;

public class MainMenu {
    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Campus Course & Records Manager (CCRM) ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Deactivate Student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    listStudents();
                    break;
                case "3":
                    deactivateStudent();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine();

        studentService.addStudent(id, name, email, regNo);
        System.out.println("Student added.");
    }

    private void listStudents() {
        System.out.println("Listing students:");
        for (var student : studentService.listAllStudents()) {
            student.printProfile();
            System.out.println("-------------");
        }
    }

    private void deactivateStudent() {
        System.out.print("Enter ID to deactivate: ");
        String id = scanner.nextLine();
        studentService.deactivateStudent(id);
        System.out.println("Student deactivated if existed.");
    }
}
