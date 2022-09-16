package com.justdoit.lamusic_webflux.student.service;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.lessoncourse.service.LessonCourseService;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final LessonCourseService lessonCourseService;

    @Transactional
    public Student createStudent(StudentDTO.StudentReq studentReq) {
        Flux<Student> student = studentRepository.save(Student.createStudent(studentReq));
        lessonCourseService.createLessonCourse(student, studentReq);

        return student;
    }
}
