package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.student.dto.StudentLessonCourseDTO;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class StudentLessonCourse {
    private String lessonClassName; // 선생님 이름으로 class(반) 이 나뉨
    private String courseTypeName; // 배울 악기
    private String lessonDay; // 수업요일
    // 수업시간
    private String lessonHour;
    private String lessonMin;
    private String lessonDuration; // 수업기간(시간)


    public static StudentLessonCourse createLessonCourse(StudentLessonCourseDTO.LessonCourseReq req) {
        return StudentLessonCourse.builder()
                .lessonClassName(req.getLessonClassName())
                .courseTypeName(req.getCourseTypeName())
                .lessonDay(req.getLessonDay())
                .lessonHour(req.getLessonHour())
                .lessonMin(req.getLessonMin())
                .lessonDuration(req.getLessonDuration())
                .build();
    }

}
