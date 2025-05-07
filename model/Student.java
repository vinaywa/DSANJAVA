package model;

public class Student {
    private int studentId;
    private int userId;
    private String enrollmentNo;
    private String department;

    public Student(int studentId, int userId, String enrollmentNo, String department) {
        this.studentId = studentId;
        this.userId = userId;
        this.enrollmentNo = enrollmentNo;
        this.department = department;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getEnrollmentNo() { return enrollmentNo; }
    public void setEnrollmentNo(String enrollmentNo) { this.enrollmentNo = enrollmentNo; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
