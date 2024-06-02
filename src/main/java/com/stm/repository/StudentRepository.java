package com.stm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stm.entity.Student;
@Repository	
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByMotherName(String motherName);

}
