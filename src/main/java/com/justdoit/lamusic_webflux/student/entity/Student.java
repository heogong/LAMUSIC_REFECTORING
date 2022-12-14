package com.justdoit.lamusic_webflux.student.entity;

import com.justdoit.lamusic_webflux.common.constant.ProcessClassType;
import com.justdoit.lamusic_webflux.common.constant.RegistrationStatus;
import com.justdoit.lamusic_webflux.common.constant.Valid;
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
    private RegistrationStatus status;
    private Valid valid;
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
    private StudentLesson studentLesson;
    private StudentPayment studentPayment;
    private List<StudentLessonCourse> lessonCourses = new ArrayList<>();

    public static Student createStudent(StudentDTO.StudentReq req) {
        return Student.builder()
                .name(req.getName())
                .birthDay(req.getBirthDay())
                .gender(req.getGender())
                .phone(req.getPhone())
                .address(req.getAddress())
                .description(req.getDescription())
                .studentLesson(StudentLesson.createStudentLesson(req))
                .studentPayment(StudentPayment.createStudentPayment(req))
                .lessonCourses(
                        req.getLessonCourseReqs().stream()
                                .map(StudentLessonCourse::createLessonCourse)
                                .collect(Collectors.toList())
                )
                .createDate(new Date())
                .updateDate(new Date())
                .build();
    }

    public Student updateStudent(StudentDTO.StudentReq req) {
        this.name = req.getName();
        this.birthDay = req.getBirthDay();
        this.gender = req.getGender();
        this.phone = req.getPhone();
        this.address = req.getAddress();
        this.description = req.getDescription();
        this.studentLesson = StudentLesson.createStudentLesson(req);
        this.studentPayment = StudentPayment.createStudentPayment(req);
        this.lessonCourses = req.getLessonCourseReqs().stream()
                .map(StudentLessonCourse::createLessonCourse)
                .collect(Collectors.toList());
        this.updateDate = new Date();
        return this;
    }

}
