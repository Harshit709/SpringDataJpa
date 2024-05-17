package com.springdatajpa.repository;

import com.springdatajpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {
    /**
     * Retrieve a list of course materials by the ID of the associated course.
     *
     * @param courseId The ID of the course for which course materials are to be retrieved.
     * @return A list of course materials associated with the specified course ID.
     */

    /** Query->SELECT * FROM course_material cm
     * JOIN course c ON
     * cm.course_id =
     * c.course_id WHERE c.course_id = :courseId*/

     List<CourseMaterial> findByCourse_CourseId(Long courseId);

    /**
     * Retrieves a list of course materials based on the given course title and a partial match of the URL.
     * Uses a native SQL query to perform the retrieval.
     *
     * @param courseTitle The title of the course to filter by.
     * @param urlPart     A partial match of the URL to filter by.
     * @return A list of course materials matching the criteria.
     */
    @Query(
            value = "SELECT cm.* FROM course_material cm " +
                    "INNER JOIN course c ON cm.course_id = c.course_id " +
                    "WHERE c.title = :courseTitle AND cm.url LIKE %:urlPart%",
            nativeQuery = true
    )
    List<CourseMaterial> findCourseMaterialsByCourseTitleAndUrlContains(@Param("courseTitle") String courseTitle,
                                                                        @Param("urlPart") String urlPart);

}