package com.justdoit.lamusic_webflux.lessoncourse.entity;

import com.justdoit.lamusic_webflux.lessoncourse.dto.LessonCourseDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Document
public class LessonCourse {

    @Id
    private String id;

    private String lessonClassName; // 선생님 이름으로 class(반) 이 나뉨
    private String courseTypeName; // 배울 악기
    private String lessonDay; // 수업요일

    // 수업시간
    private String lessonHour;
    private String lessonMin;

    // 수업기간(시간)
    private String lessonDuration;

    private Student student;

    public static LessonCourse createLessonCourse(LessonCourseDTO.LessonCourseReq req) {
        return LessonCourse.builder()
                .lessonClassName(req.getLessonClassName())
                .courseTypeName(req.getCourseTypeName())
                .lessonDay(req.getLessonDay())
                .lessonHour(req.getLessonHour())
                .lessonMin(req.getLessonMin())
                .lessonDuration(req.getLessonDuration())
                .build();
    }

}
