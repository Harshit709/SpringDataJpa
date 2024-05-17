package com.springdatajpa.service.impl;

import com.springdatajpa.dto.response.TeacherResponse;
import com.springdatajpa.repository.TeacherRepository;
import com.springdatajpa.service.TeacherService;
import com.springdatajpa.dto.request.TeacherRequest;
import com.springdatajpa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.springdatajpa.utils.erroreMassage.ERROR_FOUND;
import static com.springdatajpa.utils.erroreMassage.NOT_FOUND;


@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {

        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        if (teacherRequest != null) {
            Teacher teacher = Teacher.builder().firstName(teacherRequest.firstName())
                    .lastName(teacherRequest.lastName())
                    .courses(teacherRequest.courses()).build();
            Teacher saveTeacher = teacherRepository.save(teacher);
            return TeacherResponse.builder()
                    .firstName(saveTeacher.getFirstName())
                    .lastName(saveTeacher.getLastName())
                    .courses(saveTeacher.getCourses())
                    .teacherId(saveTeacher.getTeacherId())
                    .build();

        } else throw new RuntimeException(ERROR_FOUND);

    }

    @Override
    public void deleteTeacher(Long id) {
        if (id != null) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
            if (optionalTeacher.isPresent()) {
                Teacher teacher = optionalTeacher.get();
                teacherRepository.delete(teacher);
            } else {
                throw new RuntimeException(NOT_FOUND  + id);
            }
        }
    }

}
