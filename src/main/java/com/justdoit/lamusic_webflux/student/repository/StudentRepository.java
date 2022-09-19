package com.justdoit.lamusic_webflux.student.repository;

import com.justdoit.lamusic_webflux.student.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
}
