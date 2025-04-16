package com.btec.binhtth_Project;
import java.util.*;

public class StudentManagement {

    // Class for one student
    static class Student {
        private String id;
        private String name;
        private double mark;

        // Create new student
        public Student(String id, String name, double mark) {
            this.id = id;
            this.name = name;
            this.mark = mark;
        }

        // Get ID
        public String getId() {
            return id;
        }

        // Get name
        public String getName() {
            return name;
        }

        // Get mark
        public double getMark() {
            return mark;
        }

        // Change name
        public void setName(String name) {
            this.name = name;
        }

        // Change mark
        public void setMark(double mark) {
            this.mark = mark;
        }

        // Get grade from mark
        public String getGrade() {
            if (mark >= 9.0) return "Excellent";
            if (mark >= 7.5) return "Very Good";
            if (mark >= 6.5) return "Good";
            if (mark >= 5.0) return "Average";
            return "Fail";
        }

        // Show student info
        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Mark: " + mark + ", Grade: " + getGrade();
        }
    }

    // Class to manage students
    static class StudentManager {
        private List<Student> students = new ArrayList<>();
        private Map<String, Student> studentMap = new HashMap<>();

        // Add student
        public void addStudent(String id, String name, double mark) {
            if (studentMap.containsKey(id)) {
                throw new IllegalArgumentException("ID " + id + " already exists!");
            }
            Student student = new Student(id, name, mark);
            students.add(student);
            studentMap.put(id, student);
            System.out.println("Added: " + student);
        }

        // Delete student by ID
        public void deleteStudent(String id) {
            Student student = studentMap.remove(id);
            if (student != null) {
                students.remove(student);
                System.out.println("Deleted: " + student);
            } else {
                throw new IllegalArgumentException("No student found with ID " + id);
            }
        }

        // Update student
        public void updateStudent(String id, String newName, double newMark) {
            Student student = studentMap.get(id);
            if (student != null) {
                student.setName(newName);
                student.setMark(newMark);
                System.out.println("Updated: " + student);
            } else {
                throw new IllegalArgumentException("No student found with ID " + id);
            }
        }

        // Find student by ID
        public Student findStudent(String id) {
            Student student = studentMap.get(id);
            if (student == null) {
                throw new IllegalArgumentException("No student found with ID " + id);
            }
            return student;
        }

        // Show all students
        public void displayStudents() {
            System.out.println("\n--- Student List ---");
            for (Student student : students) {
                System.out.println(student);
            }
        }

        // Show students from highest mark to lowest
        public void displayRanking() {
            List<Student> sorted = new ArrayList<>(students);
            sorted.sort((s1, s2) -> Double.compare(s2.getMark(), s1.getMark()));
            System.out.println("\n--- Student Ranking ---");
            int i = 1;
            for (Student student : sorted) {
                System.out.println(i++ + ". " + student);
            }
        }
    }

    // Start program
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        System.out.println("Add students:\n");

        // Add 3 students
        manager.addStudent("BC002", "Phung La Minh", 6.0);
        manager.addStudent("BC001", "Tram Vu Anh", 8.5);
        manager.addStudent("BC003", "Doan Vu Lan", 9.2);

        // Show all students
        manager.displayStudents();

        // Show student ranking
        System.out.println("\nSort and show ranking by marks:");
        manager.displayRanking();

        // Find student
        System.out.println("\nFind student with ID BC002:");
        Student found = manager.findStudent("BC002");
        System.out.println("Found: " + found);

        // Update student
        System.out.println("\nUpdate student BC002:");
        manager.updateStudent("BC002", "Phung La Minh Updated", 7.8);

        // Show students again
        manager.displayStudents();

        // Delete student
        System.out.println("\nDelete student with ID BC001:");
        manager.deleteStudent("BC001");

        // Show final list
        manager.displayStudents();

        System.out.println("\nBUILD SUCCESSFUL (total time: 0 seconds)");
    }
}

