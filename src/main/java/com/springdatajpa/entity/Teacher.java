package com.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Entity class representing a Teacher.
 */
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String firstName; // First name of the teacher.

    private String lastName; // Last name of the teacher.

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private List<Course> courses= new ArrayList<>(); // List of courses associated with the teacher.
}
