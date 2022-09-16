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
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String birthDay;
    private String gender;
    private String phone;
    private String address;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private UseYN useYn;

    private String job;

    @Enumerated(EnumType.STRING)
    private ProcessClassType processClassType; // 입학과정

    // TODO 확인필요 : student sname
//    private String sName;

    // TODO 어떻게 처리할지 확인필요 : student attCnt
//    private int attCnt;

    private String parentName;
    private String parentPhone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @OneToMany(mappedBy = "student")
    private List<LessonCourse> lessonCourses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "LESSON_ID")
    private StudentLesson lesson;

    public static Student createStudent(StudentDTO.StudentReq req) {
//        req.subscribeOn(Schedulers.boundedElastic()).subscribe(q -> {
//            Student.builder()
//                    .name(q.getName())
//                    .birthDay(q.getBirthDay())
//                    .gender(q.getGender())
//                    .phone(q.getPhone())
//                    .address(q.getAddress())
//                    .description(q.getDescription())
//                    .lesson(StudentLesson.createStudentLesson(q))
//                    .build();
//        });
        return Student.builder()
                .name(req.getName())
                .birthDay(req.getBirthDay())
                .gender(req.getGender())
                .phone(req.getPhone())
                .address(req.getAddress())
                .description(req.getDescription())
                .lesson(StudentLesson.createStudentLesson(req))
                .build();

    }

}
