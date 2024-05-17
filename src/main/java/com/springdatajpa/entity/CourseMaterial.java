package com.springdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

/**
 * Entity class representing a Course Material.
 */
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseMaterialId;

    private String authorName;
    private String url;

    /**
     * One-to-one mapping with Course entity.
     * FetchType is set to EAGER to avoid lazy fetching exceptions.
     */
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonIgnore // Ignore serialization to prevent recursive serialization
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;
}
