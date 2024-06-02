package com.stm.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private int courseId;
	private String courseName;
	private int durationInMonths;
	private double fee;
	private String faculty;
	private LocalDate courseStartedDate;
//	private int studentId;
//	private String studentName;
//	private String fatherName;
//	private String motherName;
//	private LocalDate dob;
//	private String emailId;
//	private String mobileNo;
//	private String gender;

}
