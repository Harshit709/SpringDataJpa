package com.springdatajpa.service;


import com.springdatajpa.dto.request.CourseRequest;
import com.springdatajpa.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseRequest request);

    List<Object[]> countCoursesByTeacherOrderByLastName();
}
