package com.springdatajpa.service.impl;

import com.springdatajpa.dto.request.CourseRequest;
import com.springdatajpa.dto.response.CourseResponse;
import com.springdatajpa.entity.Course;
import com.springdatajpa.entity.CourseMaterial;
import com.springdatajpa.entity.Student;
import com.springdatajpa.entity.Teacher;
import com.springdatajpa.repository.CourseMaterialRepository;
import com.springdatajpa.repository.CourseRepository;
import com.springdatajpa.repository.StudentRepository;
import com.springdatajpa.repository.TeacherRepository;
import com.springdatajpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springdatajpa.utils.erroreMassage.ERROR_FOUND;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public CourseResponse createCourse(CourseRequest request) {
        List<Student> savedStudents = request.students().stream().map(studentRequest ->
                        Student.builder().firstName(studentRequest.getFirstName()).lastName(studentRequest.getLastName()).
                                emailId(studentRequest.getEmailId()).build()).
                map(studentRepository::save).toList();
        Teacher teacher = Teacher.builder().
                firstName(request.teacher().getFirstName()).lastName(request.teacher().getLastName()).build();
        Teacher savedTeacher = teacherRepository.save(teacher);
        Course course = Course.builder().title(request.title()).credit(request.credit()).students(savedStudents).teacher(savedTeacher).build();
        Course saveCourse = courseRepository.save(course);
        return CourseResponse.builder().courseId(saveCourse.getCourseId()).credit(saveCourse.getCredit()).title(saveCourse.getTitle())
                .students(saveCourse.getStudents()).teacher(saveCourse.getTeacher()).build();

    }

    @Override
    public List<Object[]> countCoursesByTeacherOrderByLastName() {
        return courseRepository.countCoursesByTeacherOrderByLastName();
    }

}

