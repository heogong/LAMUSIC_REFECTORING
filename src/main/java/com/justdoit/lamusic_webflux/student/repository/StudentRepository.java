package com.justdoit.lamusic_webflux.student.repository;

import com.justdoit.lamusic_webflux.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
}
