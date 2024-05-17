package com.springdatajpa.dto.response;

import lombok.Builder;
import com.springdatajpa.entity.Course;

import java.util.List;

@Builder
public record TeacherResponse(Long teacherId,String firstName,
                              String lastName, List<Course> courses) {
}
