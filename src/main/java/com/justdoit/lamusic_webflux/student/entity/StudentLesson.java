package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class StudentLesson {

    @Id
    @GeneratedValue
    private Long id;

    // 수강 시작일
    private String startLessonDate;

    // 수강 종료일
    private String endLessonDate;

    // 수강 기간
    private int classDates;
    private int lessonTerm;
    private int lessonWeek;

//    @OneToOne(mappedBy = "StudentLesson")
//    private Student student;

    public static StudentLesson createStudentLesson(StudentDTO.StudentReq req) {
        return StudentLesson.builder()
//                .student(student)
                .startLessonDate(req.getStartLessonDate())
                .endLessonDate(req.getEndLessonDate())
                .classDates(req.getClassDates())
                .lessonTerm(req.getLessonTerm())
                .lessonWeek(req.getLessonWeek())
                .build();
    }
}
