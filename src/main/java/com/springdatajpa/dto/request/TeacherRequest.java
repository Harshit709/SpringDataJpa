package com.springdatajpa.dto.request;

import lombok.Builder;
import com.springdatajpa.entity.Course;

import java.util.List;

@Builder
public record TeacherRequest(String firstName,
                             String lastName,
                             List<Course> courses) {
}
