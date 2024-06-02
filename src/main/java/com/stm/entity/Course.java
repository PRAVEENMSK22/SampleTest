package com.stm.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name="course")
@Data
public class Course {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="course_id")
private int courseId;
@Column(name="course_nm")	
private String courseName;
@Column(name="duration_in_months")
private int durationInMonths;
private double fee;
private String faculty;
@Column(name="course_start_dt")
private LocalDate courseStartedDate;
//@OneToOne
//@JoinColumn(name="std_id")
//private Student student;

}
