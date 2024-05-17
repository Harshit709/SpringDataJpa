package com.springdatajpa.controller;

import com.springdatajpa.dto.response.CourseMaterialResposne;
import com.springdatajpa.service.CourseMaterialService;
import com.springdatajpa.dto.request.CourseMaterialRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springdatajpa.utils.UrlConstants.*;

/**
 * Controller class for handling Course Material related endpoints.
 */
@RestController
@RequestMapping(URL_COURSE_MATERIAL)
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @Autowired
    public CourseMaterialController(CourseMaterialService courseMaterialService) {
        this.courseMaterialService = courseMaterialService;
    }

    /**
     * Endpoint for creating a new course material.
     *
     * @param courseMaterialRequest The request body containing information about the new course material.
     * @return ResponseEntity containing the response body with details of the created course material and HTTP status 201 (Created).
     */
    @PostMapping(CREATE_COURSE_MATERIAL)
    public ResponseEntity<CourseMaterialResposne> createCourseMaterial(@RequestBody CourseMaterialRequest courseMaterialRequest) {
        if (courseMaterialRequest != null) {
            CourseMaterialResposne courseMaterialResposne = courseMaterialService.createCourseMaterial(courseMaterialRequest);
            return new ResponseEntity<>(courseMaterialResposne, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for retrieving course materials by course ID.
     *
     * @param courseId The ID of the course.
     * @return ResponseEntity containing the response body with a list of course materials for the specified course
     * and HTTP status 200 (OK), or HTTP status 404 (Not Found) if no course materials are found.
     */
    @GetMapping(COURSE_MATERIAL_BY_COURSE_ID)
    public ResponseEntity<List<CourseMaterialResposne>> getCourseMaterialsByCourseId(@PathVariable Long courseId) {
        List<CourseMaterialResposne> courseMaterials = courseMaterialService.getCourseMaterialsByCourseId(courseId);
        if (!courseMaterials.isEmpty()) {
            return new ResponseEntity<>(courseMaterials, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for retrieving course materials by course title and URL contains.
     *
     * @param courseTitle The title of the course.
     * @param urlPart     The URL part to search for within the course materials.
     * @return ResponseEntity containing the response body with a list of course materials that match the given title and URL part
     * and HTTP status 200 (OK), or HTTP status 404 (Not Found) if no course materials are found.
     */
    @GetMapping(COURSE_MATERIAL_BY_COURSE_TITLE_AND_URL_CONTAINS)
    public ResponseEntity<List<CourseMaterialResposne>> getCourseMaterialsByCourseTitleAndUrlContains(
            @RequestParam String courseTitle,
            @RequestParam String urlPart
    ) {
        List<CourseMaterialResposne> courseMaterials = courseMaterialService.getCourseMaterialsByCourseTitleAndUrlContains(courseTitle, urlPart);
        if (!courseMaterials.isEmpty()) {
            return new ResponseEntity<>(courseMaterials, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
