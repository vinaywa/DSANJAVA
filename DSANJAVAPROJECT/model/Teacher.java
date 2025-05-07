package model;

public class Teacher {
    private int teacherId;
    private int userId;
    private String subject;

    public Teacher(int teacherId, int userId, String subject) {
        this.teacherId = teacherId;
        this.userId = userId;
        this.subject = subject;
    }

    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
