package com.justdoit.lamusic_webflux.attendance.service;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.attendance.repository.AttendanceRepository;
import com.justdoit.lamusic_webflux.common.UseYN;
import com.justdoit.lamusic_webflux.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final StudentService studentService;

    public Flux<AttendanceDTO.AttendanceResp> createAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        log.info("## service start : {}", Thread.currentThread().getName());

        Flux<AttendanceDTO.AttendanceResp> result = attendanceReq.flatMap(
                req -> Mono.just(req.getAttendanceType()).flatMap(
                        attendanceTypes -> attendanceRepository.insert(
                                attendanceTypes.stream()
                                        .map(attendanceType -> Attendance.createAttendance(req, attendanceType))
                                        .collect(Collectors.toList())
                        ).map(AttendanceDTO.AttendanceResp::createAttendanceResp).collectList()
                )).flatMapMany(Flux::fromIterable);

        log.info("## service end : {}", Thread.currentThread().getName());
        return result;
    }

    public Flux<AttendanceDTO.AttendanceResp> updateAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {

        Flux<AttendanceDTO.AttendanceResp> newValidAttendance = attendanceReq.flatMap(
                req -> Mono.just(req.getAttendanceType()).flatMap(
                        attendanceTypes -> attendanceRepository.insert(
                                attendanceTypes.stream()
                                        .map(attendanceType -> Attendance.createAttendance(req, attendanceType))
                                        .collect(Collectors.toList())
                        ).map(AttendanceDTO.AttendanceResp::createAttendanceResp).collectList()
                )).flatMapMany(Flux::fromIterable);

        Flux<AttendanceDTO.AttendanceResp> unValidAttendance = attendanceReq.map(AttendanceDTO.AttendanceReq::getDeleteAttendance)
                .flatMapMany(Flux::fromIterable)
                .flatMap(attendanceRepository::findById)
                .flatMap(attendance -> attendanceRepository.save(attendance.initAttendance()))
                .map(AttendanceDTO.AttendanceResp::createAttendanceResp);


        return newValidAttendance.mergeWith(unValidAttendance);
    }

    public Mono<AttendanceDTO.AttendanceStudentStatusResp> getAttendance(String toDate, String id) {
       return attendanceRepository.findByAttendanceDateAndStudentId(toDate, id)
               .filter(attendance -> attendance.getUseYN() == UseYN.Y).collectList()
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
