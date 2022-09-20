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
                        .map(StudentDTO.StudentResp::createStudentResp)
        ).log();

        log.info("## end :{}", Thread.currentThread().getName());
        return result;
    }

    public Mono<StudentDTO.StudentResp> getStudent(String id) {
        return studentRepository.findById(id)
                .map(StudentDTO.StudentResp::createStudentResp);
    }

    public Flux<StudentDTO.StudentResp> getAllStudent() {
        log.info("## start service :{}", Thread.currentThread().getName());

        Flux<StudentDTO.StudentResp> resultFlux = studentRepository.findAll()
                .map(StudentDTO.StudentResp::createStudentResp).log();

        log.info("## end service :{}", Thread.currentThread().getName());
        return resultFlux;
    }

    public Mono<StudentDTO.StudentResp> updateStudent(String id, Mono<StudentDTO.StudentReq> studentReq) {
        return studentReq.flatMap(req -> studentRepository.findById(id)
                .flatMap(student -> studentRepository.save(student.updateStudent(req)))
                .map(StudentDTO.StudentResp::createStudentResp));
    }
}
