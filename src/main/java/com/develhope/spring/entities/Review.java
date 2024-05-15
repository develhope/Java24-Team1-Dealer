package com.develhope.spring.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User student;
    @ManyToOne
    private Course course;
    @Column(nullable = false)
    private String review;

    public Review(Long id, User student, Course course, String review) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.review = review;
    }

    public Review() {
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
