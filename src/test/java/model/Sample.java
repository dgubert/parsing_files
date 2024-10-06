package model;

import java.util.List;

public class Sample {
    private String course_name;
    private List<Student> students;

    public String getCourse_name() {
        return course_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
