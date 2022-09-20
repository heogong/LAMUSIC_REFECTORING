package com.justdoit.lamusic_webflux.attendance.repository;

import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AttendanceRepository extends ReactiveMongoRepository<Attendance, String> {

    Flux<Attendance> findByStudentId(String id);
    Flux<Attendance> findByAttendanceDateAndStudentId(String toDate, String studentId);
}
