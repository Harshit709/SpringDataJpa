package com.springdatajpa.service.impl;

import com.springdatajpa.dto.response.CourseMaterialResposne;
import com.springdatajpa.service.CourseMaterialService;
import com.springdatajpa.dto.request.CourseMaterialRequest;
import com.springdatajpa.entity.CourseMaterial;
import com.springdatajpa.repository.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springdatajpa.utils.erroreMassage.ERROR_FOUND;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;

    @Autowired
    public CourseMaterialServiceImpl(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }

    @Override
    public CourseMaterialResposne createCourseMaterial(CourseMaterialRequest courseMaterialRequest) {

        if (courseMaterialRequest != null) {
            CourseMaterial courseMaterial = CourseMaterial.builder()
                    .authorName(courseMaterialRequest.authorName())
                    .url(courseMaterialRequest.url())
                    .course(courseMaterialRequest.course())
                    .build();

            CourseMaterial savedCourseMaterial = courseMaterialRepository.save(courseMaterial);
            return CourseMaterialResposne.builder()
                    .courseMaterialId(savedCourseMaterial.getCourseMaterialId())
                    .authorName(savedCourseMaterial.getAuthorName())
                    .course(savedCourseMaterial.getCourse())
                    .url(courseMaterial.getUrl())
                    .build();
        } else {
            throw new RuntimeException(ERROR_FOUND);
        }
    }


    @Override
    public List<CourseMaterialResposne> getCourseMaterialsByCourseId(Long courseId) {
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findByCourse_CourseId(courseId);

        return courseMaterials.stream()
                .map(this::mapToCourseMaterialResponse)
                .toList();

    }

    @Override
    public List<CourseMaterialResposne> getCourseMaterialsByCourseTitleAndUrlContains(String courseTitle, String url) {
        if (courseTitle != null && url != null) {
            List<CourseMaterial> courseMaterial = courseMaterialRepository.
                    findCourseMaterialsByCourseTitleAndUrlContains(courseTitle, url);
            return courseMaterial.stream()
                    .map(this::mapToCourseMaterialResponse)
                    .toList();
        } else throw new RuntimeException(ERROR_FOUND);

    }


    private CourseMaterialResposne mapToCourseMaterialResponse(CourseMaterial courseMaterial) {
        return CourseMaterialResposne.builder()
                .courseMaterialId(courseMaterial.getCourseMaterialId())
                .authorName(courseMaterial.getAuthorName())
                .url(courseMaterial.getUrl())
                .course(courseMaterial.getCourse())
                .build();
    }
}

