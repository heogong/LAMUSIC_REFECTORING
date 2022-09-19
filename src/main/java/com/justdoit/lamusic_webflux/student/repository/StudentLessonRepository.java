package com.justdoit.lamusic_webflux.student.repository;

import com.justdoit.lamusic_webflux.student.entity.StudentLesson;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLessonRepository extends ReactiveCrudRepository<StudentLesson, String> {
}
