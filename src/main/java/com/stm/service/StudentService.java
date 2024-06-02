package com.stm.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stm.entity.Course;
import com.stm.entity.Student;
import com.stm.repository.StudentRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class StudentService {
	@Autowired	
private StudentRepository studentRepository;
	public Student saveStudent(Student student) {
		return  studentRepository.save(student);
	}
	
	public void deleteById(int studentId) {
		studentRepository.deleteById(studentId);
	}
	
	public List<Student> serchByName(String motherName) {
		return studentRepository.findByMotherName(motherName);
	}
	
	public List<Student> findAll(){
		return studentRepository.findAll();
	}
	
	public Student getById(int studentId) {
		return studentRepository.findById(studentId).get();
	}
	
	public void  exportToExcel(HttpServletResponse response) throws IOException {
		
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("Course Information");
		HSSFRow headerRow=sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("StudentId");
		headerRow.createCell(1).setCellValue("StudentName");
		headerRow.createCell(2).setCellValue("FatherName");
		headerRow.createCell(3).setCellValue("MotherName");
		headerRow.createCell(4).setCellValue("Date of Birth");
		headerRow.createCell(5).setCellValue("EmailId");
		headerRow.createCell(6).setCellValue("MobileNo");
		headerRow.createCell(7).setCellValue("Gender");
		
		List<Student> students=studentRepository.findAll();
		
		int rowCount=1;
		for (Student student : students) {
			
			HSSFRow row=sheet.createRow(rowCount);
			row.createCell(0).setCellValue(student.getStudentId());
			row.createCell(1).setCellValue(student.getStudentName());
			row.createCell(2).setCellValue(student.getFatherName());
			row.createCell(3).setCellValue(student.getMotherName());
			row.createCell(4).setCellValue(student.getDob());
			row.createCell(5).setCellValue(student.getEmailId());
			row.createCell(6).setCellValue(student.getMobileNo());
			row.createCell(7).setCellValue(student.getGender());
			rowCount ++;
		}
		 
		       ServletOutputStream out=response.getOutputStream();
		       workbook.write(out);
		       workbook.close();
		       out.close();
	}
	
	
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {

		Document doc = new Document();
		doc.open();
		PdfWriter writer ;
		ServletOutputStream out = response.getOutputStream();
		writer=PdfWriter.getInstance(doc, out);
		writer.open();
		PdfPTable table = new PdfPTable(8);
		table.addCell("Student Id");
		table.addCell("Student Name");
		table.addCell("Father Name");
		table.addCell("Mother Name");
		table.addCell("Date of Birth");
		table.addCell("Email Id");
		table.addCell("Mobile No");
		table.addCell("Gender");

		List<Student> students = studentRepository.findAll();

		for (Student student : students) {
			table.addCell(String.valueOf(student.getStudentId()));
			table.addCell(student.getStudentName());
			table.addCell(student.getFatherName());
			table.addCell(student.getMotherName());
			table.addCell(String.valueOf(student.getDob()));;
			table.addCell(student.getEmailId());
			table.addCell(student.getMobileNo());
			table.addCell(student.getGender());
			
			

		}
		doc.open();
		doc.add(table);
		
		doc.close();
	}

}
