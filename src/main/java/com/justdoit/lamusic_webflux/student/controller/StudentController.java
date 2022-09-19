package com.justdoit.lamusic_webflux.student.controller;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;

//    @PostMapping("/student")
//    public Mono<StudentDTO.StudentResp> createStudent(@RequestBody StudentDTO.StudentReq req) {
//        return studentService.createStudent(req);
//    }

//    @GetMapping("/student/{id}")
//    public Mono<StudentDTO.StudentResp> getStudent(@PathVariable String id) {
//        return studentService.getStudent(id).log();
//    }
}
