package com.justdoit.lamusic_webflux.student.service;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Mono<StudentDTO.StudentResp> createStudent(Mono<StudentDTO.StudentReq> studentReq) {
        log.info("## start :{}", Thread.currentThread().getName());

        Mono<StudentDTO.StudentResp> result = studentReq.flatMap(
                req -> studentRepository.insert(Student.createStudent(req))
                        .flatMap(student -> Mono.just(StudentDTO.StudentResp.createStudentResp(student))
                )
        ).log();

        log.info("## end :{}", Thread.currentThread().getName());
        return result;
    }

    public Mono<StudentDTO.StudentResp> getStudent(String id) {
        return studentRepository.findById(id)
                .flatMap(student -> Mono.just(StudentDTO.StudentResp.createStudentResp(student))
        );
    }

    public Flux<StudentDTO.StudentResp> getAllStudent() {
        return studentRepository.findAll().flatMap(
                student -> Mono.just(StudentDTO.StudentResp.createStudentResp(student))
        );
    }

    public Mono<StudentDTO.StudentResp> updateStudent(Mono<StudentDTO.StudentReq> studentReq) {
        return studentReq.flatMap(req -> studentRepository.findById(req.getId())
                .flatMap(student -> studentRepository.save(student.updateStudent(req)))
                .flatMap(result -> Mono.just(StudentDTO.StudentResp.createStudentResp(result))));
    }
}
