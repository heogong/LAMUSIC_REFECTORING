package com.justdoit.lamusic_webflux.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StudentLessonCourseDTO {

    @AllArgsConstructor
    @Getter
    public static class LessonCourseReq {
        private String lessonClassName;
        private String courseTypeName;
        private String lessonDay;
        private String lessonHour;
        private String lessonMin;
        private String lessonDuration;
    }
}
