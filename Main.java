package nghianghia;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudentInformationSystem nghia = new StudentInformationSystem();
        Scanner scanner = new Scanner(System.in);
        inputStudentDetails(scanner, nghia);
        displayMenu(scanner, nghia);

        scanner.close();
    }

    public static void inputStudentDetails(Scanner scanner, StudentInformationSystem nghia) {
        // Input number of students
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Input student details
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Student Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            nghia.addStudent(new Student(id, name, marks));
        }

        // Display student details and rankings
        System.out.println("\nStudent Details and Rankings:");
        for (Student student : nghia.getStudents()) {
            System.out.println(student);
        }
    }

    public static void displayMenu(Scanner scanner, StudentInformationSystem nghia) {
        // Menu for sorting, searching, deleting, and editing
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Sort Students by Marks");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Delete Student by ID");
            System.out.println("4. Edit Student Details");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    nghia.sortStudents();
                    System.out.println("Students sorted by marks in descending order.");
                    // Display sorted students
                    System.out.println("\nSorted Student List:");
                    for (Student student : nghia.getStudents()) {
                        System.out.println(student);
                    }
                    break;
                case 2:
                    System.out.print("Enter Student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = nghia.searchStudentById(searchId);
                    if (foundStudent != null) {
                        System.out.println("Found Student - " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    boolean deleted = nghia.deleteStudent(deleteId);
                    if (deleted) {
                        System.out.println("Student with ID " + deleteId + " has been deleted.");
                    } else {
                        System.out.println("Student with ID " + deleteId + " not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter new Student Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Student Marks: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    nghia.editStudent(editId, newName, newMarks);
                    System.out.println("Student with ID " + editId + " has been updated.");
                    break;
                case 5:
                    System.out.println("\nUpdated Student List:");
                    for (Student student : nghia.getStudents()) {
                        System.out.println(student);
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
