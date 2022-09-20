package com.justdoit.lamusic_webflux.attendance.service;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.attendance.repository.AttendanceRepository;
import com.justdoit.lamusic_webflux.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentService studentService;

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

    public Mono<AttendanceDTO.AttendanceStudentStatusResp> getAttendance(String toDate, String id) {
       return attendanceRepository.findByAttendanceDateAndStudentId(toDate, id).collectList()
               .flatMap(attendances -> studentService.getStudent(id)
                       .map(studentResp -> AttendanceDTO.AttendanceStudentStatusResp.createAttendanceStudentStatusResp(studentResp, attendances))
               );
    }

    public Flux<AttendanceDTO.AttendanceResp> initAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .flatMap(attendance -> attendanceRepository.save(attendance.initAttendance()))
                .map(AttendanceDTO.AttendanceResp::createAttendanceResp);
    }
}
