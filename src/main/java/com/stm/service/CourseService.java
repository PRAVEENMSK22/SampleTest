package com.stm.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stm.dto.CourseDto;
import com.stm.entity.Course;
import com.stm.repository.CourseRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public CourseDto serchById(int courseId) {
		Course course = courseRepository.findById(courseId).get();
		CourseDto courseDto = new CourseDto();
		BeanUtils.copyProperties(course, courseDto);
		return courseDto;
	}

	public void deleteById(int courseId) {
		courseRepository.deleteById(courseId);
	}
	public Optional<Course> serchByStudentId(int studentId) {
		Optional<Course> course = courseRepository.findById(studentId);
		return course;
	}

	public CourseDto saveCourse(Course course) {
		Course data = courseRepository.save(course);
		CourseDto courseDto = new CourseDto();
		BeanUtils.copyProperties(data, courseDto);
		return courseDto;
	}

	public List<Course> findAll() {
		List<Course> data = courseRepository.findAll();

		return data;
	}

	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {

		Document doc = new Document();
		doc.open();
		PdfWriter writer ;
		ServletOutputStream out = response.getOutputStream();
		writer=PdfWriter.getInstance(doc, out);
		writer.open();
		PdfPTable table = new PdfPTable(6);
		table.addCell("Course Id");
		table.addCell("Course Name");
		table.addCell("Duration");
		table.addCell("Fee");
		table.addCell("Faculty");
		table.addCell("Course Start Date");

		List<Course> courses = courseRepository.findAll();

		for (Course course : courses) {
			table.addCell(String.valueOf(course.getCourseId()));
			table.addCell(course.getCourseName());
			table.addCell(String.valueOf(course.getDurationInMonths()));
			table.addCell(String.valueOf(course.getFee()));
			table.addCell(String.valueOf(course.getCourseStartedDate()));
			table.addCell(course.getFaculty());

		}
		doc.open();
		doc.add(table);
		
		doc.close();
	}

	public void exportToExcel(HttpServletResponse response) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Course _Information");
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Course Id");
		headerRow.createCell(1).setCellValue("Course Name");
		headerRow.createCell(2).setCellValue("Duration");
		headerRow.createCell(3).setCellValue("Fee");
		headerRow.createCell(4).setCellValue("Faculty");
		headerRow.createCell(5).setCellValue("Course Start Date");

		List<Course> courses = courseRepository.findAll();
		int rowCount = 1;
		
		for (Course course : courses) {
			HSSFRow row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(String.valueOf(course.getCourseId()));
			row.createCell(1).setCellValue(course.getCourseName());
			row.createCell(2).setCellValue(String.valueOf(course.getDurationInMonths()));
			row.createCell(3).setCellValue(String.valueOf(course.getFee()));
			row.createCell(4).setCellValue(course.getFaculty());
			row.createCell(5).setCellValue(String.valueOf(course.getCourseStartedDate()));
			
			rowCount ++;
		}

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		workbook.close();
		out.close();
		
	}

}
