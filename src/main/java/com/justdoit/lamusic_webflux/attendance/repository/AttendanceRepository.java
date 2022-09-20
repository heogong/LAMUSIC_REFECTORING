package com.justdoit.lamusic_webflux.attendance.repository;

import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends ReactiveMongoRepository<Attendance, String> {
}
