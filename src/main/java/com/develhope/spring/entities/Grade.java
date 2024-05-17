package com.develhope.spring.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User student;
    @ManyToOne(optional = false)
    private Course course;
    @Column(nullable = false)
    private String grade;
    @Column
    private Boolean finishedCourse;

    public Grade(Long id, User student, Course course, String grade, Boolean finishedCourse) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.finishedCourse = finishedCourse;
    }

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getFinishedCourse() {
        return finishedCourse;
    }

    public void setFinishedCourse(Boolean finishedCourse) {
        this.finishedCourse = finishedCourse;
    }
}
