package com.springdatajpa.repository;

import jakarta.transaction.Transactional;
import com.springdatajpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Finds students by their first name.
     *
     * @param firstName The first name of the students to search for.
     * @return A list of students with the given first name.
     */
    List<Student> findByFirstName(String firstName);

    /**
     * Finds students whose first name contains the given keyword.
     *
     * @param keyword The keyword to search for in first names.
     * @return A list of students whose first name contains the keyword.
     */
    List<Student> findByFirstNameContaining(String keyword);

    /**
     * Finds students by their first name and last name.
     *
     * @param firstName The first name of the students to search for.
     * @param lastName  The last name of the students to search for.
     * @return A list of students with the given first and last names.
     */
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * its an jpql query for find student object by its emailId
     * Finds a student by their email address using JPQL.
     *
     * @param emailId The email address of the student.
     * @return The student with the given email address.
     */
    @Query("Select s from Student s where emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    /**
     * Retrieves the first name of a student by their email address using JPQL.
     *
     * @param emailId The email address of the student.
     * @return The first name of the student with the given email address.
     */
    @Query("Select s.firstName from Student s where emailId = ?1")
    String getOnlyFirstNameOfStudentByEmail(String emailId);


    /**
     * Updates the first name of a student by their email address.
     *
     * @param firstName The new first name of the student.
     * @param emailId   The email address of the student.
     * @return The number of affected rows.
     */
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student tb set tb.first_name =:firstName " +
                    "where tb.email_address=:emailId  "
            , nativeQuery = true
    )
    int updateStudentNameByEmail(@Param("firstName") String firstName, @Param("emailId") String emailId);

    /**
     * Finds students whose student ID is greater than or equal to the given age.
     *
     * @param id The minimum id
     * @return A list of students whose student ID is greater than or equal to the given id.
     */
    List<Student> findByStudentIdGreaterThanEqual(int id);

    /**
     * Finds students whose first name contains the given keyword with pagination.
     *
     * @param keyword  The keyword to search for in first names.
     * @param pageable Pagination information.
     * @return A page of students whose first name contains the keyword.
     */
    Page<Student> findByFirstNameContaining(String keyword, Pageable pageable);
}
