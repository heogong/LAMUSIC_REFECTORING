package com.justdoit.lamusic_webflux.attendance.dto;

import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.common.AttendanceType;
import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceDTO {

    @AllArgsConstructor
    @Getter
    public static class AttendanceReq {
        private String attendanceDate; // YYYYMMDD
        private List<AttendanceType> attendanceType;
        private String attendanceMemo;
        private String studentId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class AttendanceResp {
        private String id;
        private String attendanceDate; // YYYYMMDD
        private AttendanceType attendanceType;
        private String attendanceMemo;

        public static AttendanceResp createAttendanceResp(Attendance attendance) {
            return AttendanceResp.builder()
                    .id(attendance.getId())
                    .attendanceDate(attendance.getAttendanceDate())
                    .attendanceType(attendance.getAttendanceType())
                    .attendanceMemo(attendance.getAttendanceMemo())
                    .build();
        }
    }
    @AllArgsConstructor
    @Builder
    @Getter
    public static class AttendanceStudentStatusResp {
        private String studentId;
        private String studentName;
//        private int attendanceCount;
        private String startLessonDate;
        private String attendanceDate;
        private List<AttendanceResp> attendanceResp;

        public static AttendanceStudentStatusResp createAttendanceStudentStatusResp(
                StudentDTO.StudentResp studentResp,
                List<Attendance> attendance
        ) {
            return AttendanceStudentStatusResp.builder()
                    .studentId("")
                    .studentName(studentResp.getName())
                    .startLessonDate(studentResp.getStartLessonDate())
                    .attendanceDate("")
                    .attendanceResp(
                            attendance.stream()
                                    .map(AttendanceResp::createAttendanceResp)
                                    .collect(Collectors.toList())
                    )
                    .build();
        }
    }

}
