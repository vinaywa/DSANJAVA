package model;

import java.sql.Date;

public class Attendance {
    private int id;
    private int studentId;
    private Date date;
    private String status;
    private int markedBy;

    public Attendance(int id, int studentId, Date date, String status, int markedBy) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.markedBy = markedBy;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getMarkedBy() { return markedBy; }
    public void setMarkedBy(int markedBy) { this.markedBy = markedBy; }
}
