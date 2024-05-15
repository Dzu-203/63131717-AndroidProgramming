package ntu.mssv63131717.Models;

public class Course {
    int imgCourse;
    String nameCourse;

    public Course(int imgCourse, String nameCourse) {
        this.imgCourse = imgCourse;
        this.nameCourse = nameCourse;
    }

    public int getImgCourse() {
        return imgCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }
}
