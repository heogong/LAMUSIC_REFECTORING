package com.justdoit.lamusic_webflux.student.dto;

import com.justdoit.lamusic_webflux.student.entity.StudentLessonCourse;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class StudentDTO {

    @AllArgsConstructor
    @Getter
    public static class StudentReq {
        private String name;
        private String birthDay;
        private String gender;
        private String phone;
        private String address;
        private String description;

        // 개인 레슨 정보
        private String startLessonDate;
        private String endLessonDate;
        private int classDates;
        private int lessonTerm;
        private int lessonWeek;

        // 결제 정보
        private String cashAmount;
        private String accountAmount;
        private String cardAmount;
        private String cardCompanyInfo;
        private String paymentMemo;
        private Date accountDate;

        // 수업정보
        List<StudentLessonCourseDTO.LessonCourseReq> lessonCourseReqs;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class StudentResp {
        private String name;
        private String birthDay;
        private String gender;
        private String phone;
        private String address;
        private String description;
        private Date createDate;
        private Date updateDate;

        // 개인 레슨 정보
        private String startLessonDate;

        // TODO StudentLessonCourseDTO resp 만들어서 리턴
        private List<StudentLessonCourse> lessonCourses;


        public static StudentResp createStudentResp(Student student) {
            return StudentResp.builder()
                    .name(student.getName())
                    .birthDay(student.getBirthDay())
                    .gender(student.getGender())
                    .phone(student.getPhone())
                    .address(student.getAddress())
                    .description(student.getDescription())
                    .startLessonDate("") // TODO 확인필요
                    .lessonCourses(student.getLessonCourses())
                    .createDate(student.getCreateDate())
                    .updateDate(student.getUpdateDate())
                    .build();
        }
    }
}
