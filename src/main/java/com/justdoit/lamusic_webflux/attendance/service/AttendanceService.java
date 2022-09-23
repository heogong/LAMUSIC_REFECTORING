package com.justdoit.lamusic_webflux.attendance.service;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.attendance.repository.AttendanceRepository;
import com.justdoit.lamusic_webflux.common.constant.Valid;
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

    public Flux<AttendanceDTO.AttendanceResp> createAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        return attendanceReq.flatMap(
                req -> Mono.just(req.getAttendanceType())
                        .flatMapMany(Flux::fromIterable)
                            .map(attendanceType -> Attendance.createAttendance(req, attendanceType))
                            .flatMap(attendanceRepository::insert)
                            .map(AttendanceDTO.AttendanceResp::createAttendanceResp).collectList()
                ).flatMapMany(Flux::fromIterable);
    }

    /**
     * webflux 에서 1개의 body 요청은 subscribe 진행 후 데이터 소진으로 기존 body 값을 유지하지 못함
     * Cold subscribe 에서 Hot subscribe 으로 변경으로 기존 body 데이터 유지
     *
     * cache : 이 모노를 핫 소스로 바꾸고 추가 구독자를 위해 마지막으로 방출된 신호를 캐시합니다. 완료 및 오류도 재생됩니다.
     */
    public Flux<AttendanceDTO.AttendanceResp> updateAttendance(Mono<AttendanceDTO.AttendanceReq> attendanceReq) {
        Mono<AttendanceDTO.AttendanceReq> hotPubAttendanceReq = attendanceReq.cache();
        Flux<AttendanceDTO.AttendanceResp> unValidAttendanceResp = hotPubAttendanceReq.map(AttendanceDTO.AttendanceReq::getDeleteAttendance)
                .flatMapMany(Flux::fromIterable)
                .flatMap(attendanceRepository::findById)
                .flatMap(attendance -> attendanceRepository.save(attendance.unValidAttendance()))
                .map(AttendanceDTO.AttendanceResp::createAttendanceResp);
        return this.createAttendance(hotPubAttendanceReq).mergeWith(unValidAttendanceResp);
    }

    public Mono<AttendanceDTO.AttendanceStudentStatusResp> getAttendance(String toDate, String id) {
       return attendanceRepository.findByAttendanceDateAndStudentId(toDate, id)
               .filter(attendance -> attendance.getValid() == Valid.Y).collectList()
               .flatMap(attendances -> studentService.getStudent(id)
                       .map(studentResp -> AttendanceDTO.AttendanceStudentStatusResp.createAttendanceStudentStatusResp(studentResp, attendances))
               );
    }

    public Flux<AttendanceDTO.AttendanceResp> unValidAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .flatMap(attendance -> attendanceRepository.save(attendance.unValidAttendance()))
                .map(AttendanceDTO.AttendanceResp::createAttendanceResp);
    }
}
