package entities;

public class Student {
    private int studentId;
    private String name;
    private String yearLevel;

    public Student() {}

    public Student(int studentId, String name, String yearLevel) {
        this.studentId = studentId;
        this.name = name;
        this.yearLevel = yearLevel;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }
}
