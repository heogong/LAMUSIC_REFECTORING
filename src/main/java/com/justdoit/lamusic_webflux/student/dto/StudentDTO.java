package com.justdoit.lamusic_webflux.student.dto;

import com.justdoit.lamusic_webflux.lessoncourse.dto.LessonCourseDTO;
import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

        // 수업정보
        List<LessonCourseDTO.LessonCourseReq> lessonCourseReqs;
    }

    @AllArgsConstructor
    @Getter
    public static class StudentResp {
        private String name;
        private String birthDay;
        private String gender;
        private String phone;
        private String address;
        private String description;
        private List<LessonCourse> lessonCourses;


        public static StudentResp createStudentResp(Student student) {
            return new StudentResp(
                    student.getName(),
                    student.getBirthDay(),
                    student.getGender(),
                    student.getPhone(),
                    student.getAddress(),
                    student.getDescription(),
                    student.getLessonCourses()
            );
        }
    }
}
