package com.stm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stm.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{

}
