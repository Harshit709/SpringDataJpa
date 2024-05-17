package com.springdatajpa.repository;

import com.springdatajpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query("SELECT c.teacher, COUNT(c) FROM Course c GROUP BY c.teacher ORDER BY c.teacher.lastName")
    List<Object[]> countCoursesByTeacherOrderByLastName();


}
