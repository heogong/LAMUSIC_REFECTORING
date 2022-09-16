package com.justdoit.lamusic_webflux.student.repository;

import com.justdoit.lamusic_webflux.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
