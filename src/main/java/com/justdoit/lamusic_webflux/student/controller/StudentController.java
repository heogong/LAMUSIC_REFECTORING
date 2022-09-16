package com.justdoit.lamusic_webflux.student.controller;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;

//    @PostMapping("/student")
//    public void createStudent(StudentDTO.StudentReq req) {
//        studentService.createStudent(req);
//    }

    @GetMapping("/student/{id}")
    public Mono<StudentDTO.StudentResp> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }
}
