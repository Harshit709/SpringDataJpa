package com.springdatajpa.service;


import com.springdatajpa.dto.response.TeacherResponse;
import com.springdatajpa.dto.request.TeacherRequest;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherRequest teacherRequest);

    void deleteTeacher (Long id);

}
