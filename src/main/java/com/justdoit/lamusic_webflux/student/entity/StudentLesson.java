package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StudentLesson {

    // 수강 시작일
    private String startLessonDate;

    // 수강 종료일
    private String endLessonDate;

    // 수강 기간
    private int classDates;
    private int lessonTerm;
    private int lessonWeek;

    public static StudentLesson createStudentLesson(StudentDTO.StudentReq req) {
        return StudentLesson.builder()
                .startLessonDate(req.getStartLessonDate())
                .endLessonDate(req.getEndLessonDate())
                .classDates(req.getClassDates())
                .lessonTerm(req.getLessonTerm())
                .lessonWeek(req.getLessonWeek())
                .build();
    }
}
