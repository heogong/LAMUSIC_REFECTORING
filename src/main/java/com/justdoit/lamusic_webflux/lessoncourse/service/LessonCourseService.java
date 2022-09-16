package com.justdoit.lamusic_webflux.lessoncourse.service;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.lessoncourse.repository.LessonCourseRepository;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LessonCourseService {

    private final LessonCourseRepository lessonCourseRepository;

    public void createLessonCourse(Student student, StudentDTO.StudentReq studentReq) {
        lessonCourseRepository.saveAll(
                studentReq.getLessonCourseReqs().stream()
                        .map(lessonCourseReq -> LessonCourse.createLessonCourse(student, lessonCourseReq))
                        .collect(Collectors.toList())
        );
    }
}
