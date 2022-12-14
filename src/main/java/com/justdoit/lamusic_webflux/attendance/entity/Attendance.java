package com.justdoit.lamusic_webflux.attendance.entity;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.common.constant.AttendanceType;
import com.justdoit.lamusic_webflux.common.constant.Valid;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@Document
public class Attendance {

    @Id
    private String id;
    private String attendanceDate; // YYYYMMDD
    private AttendanceType attendanceType;
    private String attendanceMemo;
    private Date createDate;
    private Date updateDate;

    private String studentId;

    private Valid valid;

    // TODO 입력 선생님, 수정 선생님

    public static Attendance createAttendance(AttendanceDTO.AttendanceReq req, AttendanceType attendanceType) {
        return Attendance.builder()
                .attendanceDate(req.getAttendanceDate())
                .attendanceType(attendanceType)
                .attendanceMemo(req.getAttendanceMemo())
                .studentId(req.getStudentId())
                .valid(Valid.Y)
                .createDate(new Date())
                .updateDate(new Date())
                .build();
    }

    public Attendance unValidAttendance() {
        this.valid = Valid.N;
        return this;
    }
}
