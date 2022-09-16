package com.justdoit.lamusic_webflux.lessoncourse.repository;

import com.justdoit.lamusic_webflux.lessoncourse.entity.LessonCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCourseRepository extends JpaRepository<LessonCourse, Long> {
}
