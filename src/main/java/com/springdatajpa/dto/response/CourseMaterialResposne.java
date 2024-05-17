package com.springdatajpa.dto.response;

import lombok.Builder;
import com.springdatajpa.entity.Course;

@Builder
public record CourseMaterialResposne(Long courseMaterialId,
                                     String authorName,
                                     Course course,
                                     String url) {

}
