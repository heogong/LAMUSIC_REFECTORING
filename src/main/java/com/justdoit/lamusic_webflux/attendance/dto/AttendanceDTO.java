package com.justdoit.lamusic_webflux.attendance.dto;

import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.common.AttendanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AttendanceDTO {

    @AllArgsConstructor
    @Getter
    public static class AttendanceReq {
        private String attendanceDate; // YYYYMMDD
        private AttendanceType attendanceType;
        private String attendanceMemo;
        private String studentId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class AttendanceResp {
        private String attendanceDate; // YYYYMMDD
        private AttendanceType attendanceType;
        private String attendanceMemo;

        public static AttendanceResp createAttendanceResp(Attendance attendance) {
            return AttendanceResp.builder()
                    .attendanceDate(attendance.getAttendanceDate())
                    .attendanceType(attendance.getAttendanceType())
                    .attendanceMemo(attendance.getAttendanceMemo())
                    .build();
        }
    }
}
