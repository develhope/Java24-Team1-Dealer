package com.develhope.spring.entities;

import com.develhope.spring.enums.CourseType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date startDate;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date finishDate;
    @Column(nullable = false)
    private Integer courseLength;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String subject;
    private String description;
    @ManyToOne
    private User tutor;
    @Column(nullable = false)
    private Boolean activeCourse;
    @Column(nullable = false)
    private CourseType courseType;

    public Course(Long id, String name, Date startDate, Date finishDate, Integer courseLength, Double price, String subject, String description, User tutor, Boolean activeCourse, CourseType courseType) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.courseLength = courseLength;
        this.price = price;
        this.subject = subject;
        this.description = description;
        this.tutor = tutor;
        this.activeCourse = activeCourse;
        this.courseType = courseType;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(Integer courseLength) {
        this.courseLength = courseLength;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    public Boolean getActiveCourse() {
        return activeCourse;
    }

    public void setActiveCourse(Boolean activeCourse) {
        this.activeCourse = activeCourse;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
