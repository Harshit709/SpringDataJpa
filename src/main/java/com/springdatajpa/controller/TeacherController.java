package com.springdatajpa.controller;

import com.springdatajpa.dto.response.TeacherResponse;
import com.springdatajpa.service.TeacherService;
import com.springdatajpa.dto.request.TeacherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.springdatajpa.utils.Massage.DELETED_TEACHER;
import static com.springdatajpa.utils.UrlConstants.CREATE_TEACHER;
import static com.springdatajpa.utils.UrlConstants.DELETE_TEACHER_BY_ID;
import static com.springdatajpa.utils.UrlConstants.URL_TEACHER;

@RestController
@RequestMapping(URL_TEACHER)
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * Endpoint for creating a new teacher.
     *
     * @param teacherRequest The request body containing information about the new teacher.
     * @return ResponseEntity containing the response body with details of the created teacher and HTTP status 201 (Created).
     */
    @PostMapping(CREATE_TEACHER)
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        TeacherResponse teacherResponse = teacherService.createTeacher(teacherRequest);
        return new ResponseEntity<>(teacherResponse, HttpStatus.CREATED);
    }

    /**
     * Endpoint for deleting a teacher by ID.
     * Note: CascadeType.ALL is used, so deleting a teacher will also delete associated courses.
     *
     * @param id The ID of the teacher to be deleted.
     * @return A custom message indicating that the teacher has been deleted.
     */
    @DeleteMapping(DELETE_TEACHER_BY_ID)
    public String deleteTeacher(@RequestParam Long id) {
        teacherService.deleteTeacher(id);
        return DELETED_TEACHER + id;
    }

}
