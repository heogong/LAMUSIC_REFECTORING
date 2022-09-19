package com.justdoit.lamusic_webflux.lessoncourse.repository;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCourseRepository extends ReactiveMongoRepository<LessonCourse, String> {
}
