package com.justdoit.lamusic_webflux.lessoncourse.entity;

import com.justdoit.lamusic_webflux.lessoncourse.dto.LessonCourseDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class LessonCourse {

    @Id
    @GeneratedValue
    private Long id;

    private String lessonClassName; // 선생님 이름으로 class(반) 이 나뉨
    private String courseTypeName; // 배울 악기
    private String lessonDay; // 수업요일

    // 수업시간
    private String lessonHour;
    private String lessonMin;

    // 수업기간(시간)
    private String lessonDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    public static LessonCourse createLessonCourse(Student student, LessonCourseDTO.LessonCourseReq req) {
        return LessonCourse.builder()
                .student(student)
                .lessonClassName(req.getLessonClassName())
                .courseTypeName(req.getCourseTypeName())
                .lessonDay(req.getLessonDay())
                .lessonHour(req.getLessonHour())
                .lessonMin(req.getLessonMin())
                .lessonDuration(req.getLessonDuration())
                .build();
    }

}
