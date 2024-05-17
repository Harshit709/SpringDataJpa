package com.springdatajpa.service.impl;

import com.springdatajpa.dto.request.StudentRequest;
import com.springdatajpa.dto.response.StudentResponse;
import com.springdatajpa.service.StudentService;
import com.springdatajpa.entity.Student;
import com.springdatajpa.repository.StudentRepository;
import com.springdatajpa.utils.erroreMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {

        Student student = Student.builder().
                lastName(studentRequest.lastName()).
                firstName(studentRequest.firstName()).
                emailId(studentRequest.emailId()).build();
        if (student != null) {
            Student createdStudent = studentRepository.save(student);
            return StudentResponse.
                    builder().firstName(createdStudent.getFirstName()).lastName(createdStudent.getLastName()).emailId(createdStudent.getEmailId()).
                    studentId(createdStudent.getStudentId()).build();
        } else
            throw new RuntimeException(erroreMassage.ERROR_FOUND);


    }

    @Override
    public List<StudentResponse> findByFirstNameAndLastName(String firstName, String lastName) {
        List<Student> existStudents = studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return existStudents.stream().map(this::mapToStudentResponse).toList();

    }

    @Override
    public List<StudentResponse> findByFistNameKeyword(String keyword) {
        if (keyword != null) {
            List<Student> students = studentRepository.findByFirstNameContaining(keyword);
            return students.stream().map(this::mapToStudentResponse).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentResponse> findStudentByIdGreaterThanEqualTo(int id) {
        if (id != 0) {
            List<Student> students = studentRepository.findByStudentIdGreaterThanEqual(id);
            return students.stream().map(this::mapToStudentResponse).toList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public StudentResponse findByOnlyFirstNameBYEmail(String email) {
        String firstName = studentRepository.getOnlyFirstNameOfStudentByEmail(email);
        if (!firstName.isEmpty()) {
            return StudentResponse.builder().firstName(firstName).build();
        } else {
            throw new RuntimeException(erroreMassage.ERROR_FOUND);
        }
    }

    @Override
    public List<StudentResponse> getStudentsWithPaginationAndSort(Pageable pageable, String keyword) {

        // Fetch students based on pagination, sorting, and filtering by keyword
        Page<Student> studentPage = studentRepository.findByFirstNameContaining(keyword, pageable);
        // Convert Page<Student> to List<StudentResponse>
        return studentPage.getContent().stream().map(this::mapToStudentResponse).toList();
    }

    @Override
    public List<StudentResponse> updateStudentNameByEmail(String newName, String email) {

        if (newName != null && email != null) {
            Integer rowsEffect = studentRepository.updateStudentNameByEmail(newName, email);
            List<Student> updatedStudent = studentRepository.findByFirstName(newName);
            return updatedStudent.stream().map(this::mapToStudentResponse).toList();

        } else {
            throw new RuntimeException(erroreMassage.ERROR_FOUND);
        }

    }

    private StudentResponse mapToStudentResponse(Student student) {
        return StudentResponse.builder().
                studentId(student.getStudentId()).
                firstName(student.getFirstName()).
                lastName(student.getLastName()).
                emailId(student.getEmailId()).build();
    }

}
