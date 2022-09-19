package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.common.ProcessClassType;
import com.justdoit.lamusic_webflux.common.Status;
import com.justdoit.lamusic_webflux.common.UseYN;
import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Document
public class Student {

    @Id
    private String id;
    private String name;
    private String birthDay;
    private String gender;
    private String phone;
    private String address;
    private String description;
    private Status status;
    private UseYN useYn;
    private String job;
    private ProcessClassType processClassType; // 입학과정

    // TODO 확인필요 : student sname
//    private String sName;

    // TODO 어떻게 처리할지 확인필요 : student attCnt
//    private int attCnt;

    private String parentName;
    private String parentPhone;
    private Date createDate;
    private Date updateDate;

    private List<LessonCourse> lessonCourses = new ArrayList<>();

    private StudentLesson lesson;

    public static Student createStudent(StudentDTO.StudentReq req) {
        return Student.builder()
                .name(req.getName())
                .birthDay(req.getBirthDay())
                .gender(req.getGender())
                .phone(req.getPhone())
                .address(req.getAddress())
                .description(req.getDescription())
                .lesson(StudentLesson.createStudentLesson(req))
                .lessonCourses(
                        req.getLessonCourseReqs().stream()
                                .map(LessonCourse::createLessonCourse
                        ).collect(Collectors.toList())
                )
                .build();
    }

}
