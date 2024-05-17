package com.springdatajpa.controller;

import com.springdatajpa.dto.request.StudentRequest;
import com.springdatajpa.dto.response.StudentResponse;
import com.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springdatajpa.utils.UrlConstants.*;

@RestController
@RequestMapping(URL_STUDENT)
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Endpoint for creating a new student.
     *
     * @param studentRequest The request body containing information about the new student.
     * @return ResponseEntity containing the response body with details of the created student and HTTP status 201 (Created).
     */
    @PostMapping(CREATE_STUDENT)
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        if (studentRequest != null) {
            StudentResponse savedStudent = studentService.createStudent(studentRequest);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for finding students by first name and last name.
     *
     * @param firstName The first name of the student to search for.
     * @param lastName  The last name of the student to search for.
     * @return ResponseEntity containing the response body with a list of matching students and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if no students are found.
     */
    @GetMapping(FIND_BY_FIRST_AND_LAST_NAME)
    public ResponseEntity<List<StudentResponse>> findByFirstNameAndLastName(@RequestParam String firstName,
                                                                            @RequestParam String lastName) {
        List<StudentResponse> studentResponses = studentService.findByFirstNameAndLastName(firstName, lastName);
        if (!studentResponses.isEmpty()) {
            return new ResponseEntity<>(studentResponses, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for finding students by a keyword in their first name.
     *
     * @param keyword The keyword to search for in the first names of students.
     * @return ResponseEntity containing the response body with a list of matching students and HTTP status 200 (OK),
     * or HTTP status 400 (Bad Request) if the request is invalid.
     */
    @GetMapping(FIND_BY_FIRST_NAME_KEYWORD)
    public ResponseEntity<List<StudentResponse>> findByKeywordInFirstName(@RequestParam String keyword) {
        List<StudentResponse> studentResponses = studentService.findByFistNameKeyword(keyword);
        if (studentResponses != null) {
            return new ResponseEntity<>(studentResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for finding students with an ID greater than or equal to the specified value.
     *
     * @param id The ID value to search for.
     * @return ResponseEntity containing the response body with a list of matching students and HTTP status 200 (OK).
     */
    @GetMapping(FIND_BY_ID_GREATER_THAN_EQUAL_TO)
    public ResponseEntity<List<StudentResponse>> findStudentByIdGreaterThanEqualTo(@RequestParam Integer id) {
        List<StudentResponse> studentResponses = studentService.findStudentByIdGreaterThanEqualTo(id);
        return new ResponseEntity<>(studentResponses, HttpStatus.OK);
    }

    /**
     * Endpoint for finding a student's first name by email.
     *
     * @param email The email address of the student to search for.
     * @return ResponseEntity containing the response body with the first name of the matching student and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if no matching student is found.
     */
    @GetMapping(FIND_FIRST_NAME_BY_EMAIL)
    public ResponseEntity<StudentResponse> getFirstNameByEmail(@RequestParam String email) {
        if (email != null) {
            StudentResponse studentResponse = studentService.findByOnlyFirstNameBYEmail(email);
            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for getting all students with pagination and sorting.
     *
     * @param pageNo      The page number (starting from 0).
     * @param pageSize    The number of students per page.
     * @param sortByField The field to sort the students by.
     * @param keyword     Optional keyword to filter students by first name.
     * @return ResponseEntity containing the response body with a list of paginated and sorted students
     * and HTTP status 200 (OK), or HTTP status 404 (Not Found) if no students are found.
     */
    @GetMapping(PAGINATION_SORTING)
    public ResponseEntity<List<StudentResponse>> getAllStudentsWithPaginationAndSort(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "firstName") String sortByField,
            @RequestParam(required = false) String keyword
    ) {
        Sort sortBy = Sort.by(sortByField);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortBy);

        // Fetch students based on pagination, sorting, and filtering by keyword
        List<StudentResponse> studentPage = studentService.getStudentsWithPaginationAndSort(pageable, keyword);

        if (!studentPage.isEmpty()) {
            return new ResponseEntity<>(studentPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for updating a student's name by email.
     *
     * @param newName The new name to update.
     * @param email   The email address of the student to update.
     * @return ResponseEntity containing the response body with a list of updated students and HTTP status 200 (OK),
     * or HTTP status 404 (Not Found) if no matching student is found.
     */
    @PutMapping(UPDATE_STUDENT_NAME_BY_EMAIL)
    public ResponseEntity<List<StudentResponse>> updateStudentNameByEmail(@RequestParam String newName, @RequestParam String email) {
        List<StudentResponse> updatedStudents = studentService.updateStudentNameByEmail(newName, email);
        if (updatedStudents != null)
            return new ResponseEntity<>(updatedStudents, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
