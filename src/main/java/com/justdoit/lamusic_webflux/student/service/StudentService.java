package com.justdoit.lamusic_webflux.student.service;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Mono<StudentDTO.StudentResp> createStudent(Mono<StudentDTO.StudentReq> studentReq) {
        return studentReq.flatMap(
                req -> studentRepository.insert(Student.createStudent(req)).flatMap(
                        student -> Mono.just(StudentDTO.StudentResp.createStudentResp(student))
                )
        );
    }

    public Mono<StudentDTO.StudentResp> getStudent(String id) {
        return studentRepository.findById(id).flatMap(
                student -> Mono.just(StudentDTO.StudentResp.createStudentResp(student))
        );
    }
}
