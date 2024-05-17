package com.springdatajpa.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlConstants {

    public static final String URL_COURSE = "course";
    public static final String URL_COURSE_MATERIAL = "courseMaterial";
    public static final String URL_STUDENT = "/student";
    public static final String URL_TEACHER = "/teacher";
    public static final String CREATE_STUDENT = "/create";
    public static final String FIND_BY_FIRST_AND_LAST_NAME = "/first-last-name";
    public static final String FIND_BY_FIRST_NAME_KEYWORD = "/keyword";
    public static final String FIND_BY_ID_GREATER_THAN_EQUAL_TO = "/find-By-Id-Greater-Than";
    public static final String FIND_FIRST_NAME_BY_EMAIL = "/find-name";
    public static final String PAGINATION_SORTING = "/find-by-page";
    public static final String UPDATE_STUDENT_NAME_BY_EMAIL = "/update-name-by-email";
    public static final String CREATE_COURSE_MATERIAL = "/create-course-material";
    public static final String COURSE_MATERIAL_BY_COURSE_ID = "/by-course/{courseId}";
    public static final String COURSE_MATERIAL_BY_COURSE_TITLE_AND_URL_CONTAINS = "/course-material-by-course-title-and-url";
    public static final String CREATE_TEACHER = "/create";
    public static final String DELETE_TEACHER_BY_ID = "/delete";
    public static final String CREATE_COURSE = "/create";
    public static final String COUNT_COURSES_BY_TEACHER_ORDER_BY_LASTNAME = "/count-group-by";


}
