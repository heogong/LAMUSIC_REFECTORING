package com.justdoit.lamusic_webflux.attendance.entity;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.common.AttendanceType;
import com.justdoit.lamusic_webflux.common.UseYN;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private UseYN useYN;

    // TODO 입력 선생님, 수정 선생님

    public static Attendance createAttendance(AttendanceDTO.AttendanceReq req, AttendanceType type) {
        return Attendance.builder()
                .attendanceDate(req.getAttendanceDate())
                .attendanceType(type)
                .attendanceMemo(req.getAttendanceMemo())
                .studentId(req.getStudentId())
                .useYN(UseYN.Y)
                .createDate(new Date())
                .updateDate(new Date())
                .build();
    }

    public Attendance updateAttendance(AttendanceDTO.AttendanceReq req) {
        this.attendanceDate = req.getAttendanceDate();
        this.attendanceType = req.getAttendanceType();
        this.attendanceMemo = req.getAttendanceMemo();
        this.updateDate = new Date();

        return this;
    }

    public Attendance initAttendance() {
        this.useYN = UseYN.N;
        return this;
    }
}
