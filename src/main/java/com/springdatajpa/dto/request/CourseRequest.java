package com.springdatajpa.dto.request;

import com.springdatajpa.entity.CourseMaterial;
import com.springdatajpa.entity.Student;
import com.springdatajpa.entity.Teacher;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseRequest(String title,
                            Integer credit,

                            Teacher teacher
        , List<Student> students) {
}
