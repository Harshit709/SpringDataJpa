package com.springdatajpa.controller;

import com.springdatajpa.dto.request.CourseRequest;
import com.springdatajpa.dto.response.CourseResponse;
import com.springdatajpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springdatajpa.utils.UrlConstants.*;

@RestController
@RequestMapping(URL_COURSE)
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(CREATE_COURSE)
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        CourseResponse courseResponse = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @GetMapping(COUNT_COURSES_BY_TEACHER_ORDER_BY_LASTNAME)
    public List<Object[]> countCoursesByTeacherOrderByLastName() {
        return courseService.countCoursesByTeacherOrderByLastName();
    }



}
