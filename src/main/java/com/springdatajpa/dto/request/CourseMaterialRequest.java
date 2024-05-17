package com.springdatajpa.dto.request;

import lombok.Builder;
import com.springdatajpa.entity.Course;

@Builder
public record CourseMaterialRequest(
        String authorName,
        Course course, String url) {
}
