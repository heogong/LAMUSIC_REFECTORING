package com.justdoit.lamusic_webflux.attendance.service;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Mono<AttendanceDTO.AttendanceResp> createAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        log.info("## service start : {}", Thread.currentThread().getName());

        Mono<AttendanceDTO.AttendanceResp> result = attendanceReq.flatMap(
                req -> attendanceRepository.insert(Attendance.createAttendance(req))
                        .map(AttendanceDTO.AttendanceResp::createAttendanceResp)).log();

        log.info("## service end : {}", Thread.currentThread().getName());
        return result;
    }

    public Mono<AttendanceDTO.AttendanceResp> updateAttendance(String id, Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        return attendanceReq.flatMap(
                req -> attendanceRepository.findById(id)
                        .flatMap(attendance -> attendanceRepository.save(attendance.updateAttendance(req)))
                        .map(AttendanceDTO.AttendanceResp::createAttendanceResp)
        );
    }

    public Mono<AttendanceDTO.AttendanceResp> getAttendance(String id) {
        return attendanceRepository.findById(id)
                .map(AttendanceDTO.AttendanceResp::createAttendanceResp);
    }
}
