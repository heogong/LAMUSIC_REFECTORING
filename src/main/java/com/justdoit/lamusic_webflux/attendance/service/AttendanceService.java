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

    public Mono<List<AttendanceDTO.AttendanceResp>> createAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        log.info("## service start : {}", Thread.currentThread().getName());

        Mono<List<AttendanceDTO.AttendanceResp>> result = attendanceReq.flatMap(
                req -> Mono.just(req.getAttendanceType()).flatMap(
                        attendanceTypes -> attendanceRepository.insert(
                                attendanceTypes.stream()
                                        .map(attendanceType -> Attendance.createAttendance(req, attendanceType))
                                        .collect(Collectors.toList())
                        ).map(AttendanceDTO.AttendanceResp::createAttendanceResp).collectList()
                ));

        log.info("## service end : {}", Thread.currentThread().getName());
        return result;
    }

    public Flux<Attendance> updateAttendance(Mono<AttendanceDTO.AttendanceUpdateReq> attendanceUpdateReq) {


        Mono<List<String>> deleteIdMonoList = attendanceUpdateReq.map(AttendanceDTO.AttendanceUpdateReq::getDeleteAttendance);




        Flux<String> aa = deleteIdMonoList.flatMapMany(Flux::fromIterable);

        Flux<Attendance> ab = aa.flatMap(attendanceRepository::findById);

        Flux<Attendance> ac = ab.flatMap(x -> attendanceRepository.save(x.initAttendance()));




//                .collectList()

//        Mono<List<Mono<AttendanceDTO.AttendanceResp>>> aa = attendanceUpdateReq.map(
//                req -> req.getDeleteAttendance().stream().map(
//                        id -> attendanceRepository.findById(id)
//                                .flatMap(attendance -> attendanceRepository.save(attendance.initAttendance()))
//                                .map(AttendanceDTO.AttendanceResp::createAttendanceResp)
//                        ).collect(Collectors.toList())
//        );

        return ac;


//        attendanceUpdateReq.map(
//                req -> req.getDeleteAttendance().stream()
//                        .map(attendanceRepository::findById)
//                        .flatMap(a -> a.)
//        ))



//                        .flatMap(attendance -> attendanceRepository.save(attendance.initAttendance()))
//                        .map(AttendanceDTO.AttendanceResp::createAttendanceResp)
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
