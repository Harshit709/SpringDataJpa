package com.springdatajpa.service;

import com.springdatajpa.dto.response.StudentResponse;
import com.springdatajpa.dto.request.StudentRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent (StudentRequest studentRequest);
    List<StudentResponse> findByFirstNameAndLastName(String firstName, String lastName);

    List<StudentResponse> findByFistNameKeyword(String keyword);

    List<StudentResponse> findStudentByIdGreaterThanEqualTo(int id);

    StudentResponse findByOnlyFirstNameBYEmail(String email);

    List<StudentResponse> getStudentsWithPaginationAndSort(Pageable pageable, String keyword);

   List<StudentResponse> updateStudentNameByEmail(String newName , String email);


}
