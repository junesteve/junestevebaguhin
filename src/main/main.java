package main;

import config.config;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            config db = new config();
            config.connectDB();

            boolean loggedIn = false;

            while (true) {
                System.out.println("\n=== WELCOME TO STUDENT SUBJECT REGISTRATION SYSTEM ===");
                System.out.println("1. Register User");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");
                int authChoice = sc.nextInt();

                switch (authChoice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String newUser = sc.next();
                        System.out.print("Enter password: ");
                        String newPass = sc.next();

                        String sqlRegister = "INSERT INTO users(username, password) VALUES (?,?)";
                        db.addRecord(sqlRegister, newUser, newPass);
                        System.out.println("✅ User registered successfully!");
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        String username = sc.next();
                        System.out.print("Enter password: ");
                        String password = sc.next();

                        String sqlLogin = "SELECT * FROM users WHERE username = ? AND password = ?";
                        boolean success = db.checkLogin(sqlLogin, username, password);

                        if (success) {
                            System.out.println("✅ Login successful!");
                            loggedIn = true;
                        } else {
                            System.out.println("❌ Invalid username or password.");
                        }
                        break;

                    case 3:
                        System.out.println("👋 Exiting program. Goodbye!");
                        return;

                    default:
                        System.out.println("⚠️ Invalid option. Try again.");
                }

                // ======================
                // MAIN MENU AFTER LOGIN
                // ======================
                while (loggedIn) {
                    System.out.println("\n=== MAIN MENU ===");
                    System.out.println("1. Student Management");
                    System.out.println("2. Subject Management");
                    System.out.println("3. Logout");
                    System.out.print("Choose option: ");
                    int mainChoice = sc.nextInt();

                    switch (mainChoice) {
                        case 1:
                            studentMenu(db, sc);
                            break;
                        case 2:
                            subjectMenu(db, sc);
                            break;
                        case 3:
                            loggedIn = false;
                            System.out.println("🔒 Logged out successfully.");
                            break;
                        default:
                            System.out.println("⚠️ Invalid option. Try again.");
                    }
                }
            }
        }
    }

    // ====================================
    // 🔹 STUDENT MENU & METHODS
    // ====================================
    public static void studentMenu(config db, Scanner sc) {
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(db, sc);
                    break;
                case 2:
                    viewStudents(db);
                    break;
                case 3:
                    updateStudent(db, sc);
                    break;
                case 4:
                    deleteStudent(db, sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void addStudent(config db, Scanner sc) {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Student Name: ");
        String studentName = sc.next();
        System.out.print("Enter Year Level: ");
        String year = sc.next();

        String sql = "INSERT INTO student(student_id, name, year_level) VALUES (?,?,?)";
        db.addRecord(sql, studentId, studentName, year);
    }

    public static void viewStudents(config db) {
        try {
            String sql = "SELECT * FROM student";
            ResultSet rs = db.getRecords(sql);
            System.out.println("\n=== STUDENT LIST ===");
            while (rs != null && rs.next()) {
                System.out.println("ID: " + rs.getInt("student_id") +
                        " | Name: " + rs.getString("name") +
                        " | Year: " + rs.getString("year_level"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing students: " + e.getMessage());
        }
    }

    public static void updateStudent(config db, Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Student Name: ");
        String newName = sc.next();
        System.out.print("Enter new Year Level: ");
        String newYear = sc.next();

        String sql = "UPDATE student SET name = ?, year_level = ? WHERE student_id = ?";
        db.updateRecord(sql, newName, newYear, id);
    }

    public static void deleteStudent(config db, Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM student WHERE student_id = ?";
        db.deleteRecord(sql, id);
    }

    // ====================================
    // 🔹 SUBJECT MENU & METHODS
    // ====================================
    public static void subjectMenu(config db, Scanner sc) {
        while (true) {
            System.out.println("\n=== SUBJECT MANAGEMENT ===");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Update Subject");
            System.out.println("4. Delete Subject");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addSubject(db, sc);
                    break;
                case 2:
                    viewSubjects(db);
                    break;
                case 3:
                    updateSubject(db, sc);
                    break;
                case 4:
                    deleteSubject(db, sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("⚠️ Invalid option. Try again.");
            }
        }
    }

    public static void addSubject(config db, Scanner sc) {
        System.out.print("Enter Subject ID: ");
        int subjectId = sc.nextInt();
        System.out.print("Enter Subject Name: ");
        String subjectName = sc.next();
        System.out.print("Enter Description: ");
        String desc = sc.next();

        String sql = "INSERT INTO subject(subject_id, subject_name, description) VALUES (?,?,?)";
        db.addRecord(sql, subjectId, subjectName, desc);
    }

    public static void viewSubjects(config db) {
        try {
            String sql = "SELECT * FROM subject";
            ResultSet rs = db.getRecords(sql);
            System.out.println("\n=== SUBJECT LIST ===");
            while (rs != null && rs.next()) {
                System.out.println("ID: " + rs.getInt("subject_id") +
                        " | Name: " + rs.getString("subject_name") +
                        " | Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing subjects: " + e.getMessage());
        }
    }

    public static void updateSubject(config db, Scanner sc) {
        System.out.print("Enter Subject ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Subject Name: ");
        String newName = sc.next();
        System.out.print("Enter new Description: ");
        String newDesc = sc.next();

        String sql = "UPDATE subject SET subject_name = ?, description = ? WHERE subject_id = ?";
        db.updateRecord(sql, newName, newDesc, id);
    }

    public static void deleteSubject(config db, Scanner sc) {
        System.out.print("Enter Subject ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM subject WHERE subject_id = ?";
        db.deleteRecord(sql, id);
    }
}
