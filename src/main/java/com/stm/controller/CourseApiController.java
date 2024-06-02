package com.stm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.stm.dto.CourseDto;
import com.stm.entity.Course;
import com.stm.service.CourseService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/course")
public class CourseApiController {
		@Autowired
	private CourseService courseService;
		
	@GetMapping(value="/{courseId}/details")
	public CourseDto serchById( @PathVariable("courseId") int courseId) {
		return courseService.serchById(courseId);
	}
	
	  @GetMapping(value="/studentId/{studentId}")
	  public Optional<Course> serchByStudentId( @PathVariable("studentId") int studentId) { 
		  return courseService.serchByStudentId(studentId);
	  }
	@PostMapping("/save")
	public CourseDto saveCourse(@RequestBody Course course) {
		return courseService.saveCourse(course);
	}
	
	@GetMapping("/findAll")
	public List<Course> findAll(){
		return courseService.findAll();	
	}
	
	
	@GetMapping("/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=course.pdf";	
		response.addHeader(headerKey, headerValue);
		courseService.exportToPdf(response);
	}
	@GetMapping("/export/xls")
	public void exportToExcel(HttpServletResponse response) throws DocumentException, IOException{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=course.xls";	
		response.addHeader(headerKey, headerValue);
		courseService.exportToExcel(response);
	}
	@DeleteMapping("delete/{courseId}")
	public void deleteById(@PathVariable("courseId") int courseId) {
		courseService.deleteById(courseId);
	}
	
}
