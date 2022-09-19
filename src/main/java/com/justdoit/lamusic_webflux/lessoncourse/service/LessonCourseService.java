package com.justdoit.lamusic_webflux.lessoncourse.service;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.lessoncourse.repository.LessonCourseRepository;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LessonCourseService {

    private final LessonCourseRepository lessonCourseRepository;

    public Flux<LessonCourse> createLessonCourse(Mono<Student> student, StudentDTO.StudentReq studentReq) {
        return lessonCourseRepository.insert(
                studentReq.getLessonCourseReqs().stream()
                        .map(LessonCourse::createLessonCourse)
                        .collect(Collectors.toList())
        );
    }
}
