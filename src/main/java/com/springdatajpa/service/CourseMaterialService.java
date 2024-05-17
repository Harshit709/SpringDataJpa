package com.springdatajpa.service;

import com.springdatajpa.dto.response.CourseMaterialResposne;
import com.springdatajpa.dto.request.CourseMaterialRequest;

import java.util.List;


public interface CourseMaterialService {

    CourseMaterialResposne createCourseMaterial(CourseMaterialRequest courseMaterialRequest);

    List<CourseMaterialResposne> getCourseMaterialsByCourseId(Long courseId);

    List<CourseMaterialResposne> getCourseMaterialsByCourseTitleAndUrlContains(String courseTitle, String url);
}
