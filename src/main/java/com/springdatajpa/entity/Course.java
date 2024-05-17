package com.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(mappedBy = "course")
    @JoinColumn(name = "courseMaterial_id", referencedColumnName = "courseMaterialId")
    private CourseMaterial courseMaterial;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;


    @ManyToMany()
    @JoinTable(name = "student_course_Map",
            joinColumns = @JoinColumn(
                    name = "course_Id",
                    referencedColumnName = "courseId"
            ), inverseJoinColumns = @JoinColumn(
            name = "student_id",
            referencedColumnName = "studentId"
    ))
    private List<Student> students;
}
