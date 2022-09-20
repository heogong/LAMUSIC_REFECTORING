package com.justdoit.lamusic_webflux.attendance.router;

import com.justdoit.lamusic_webflux.attendance.dto.AttendanceDTO;
import com.justdoit.lamusic_webflux.attendance.entity.Attendance;
import com.justdoit.lamusic_webflux.attendance.service.AttendanceService;
import com.justdoit.lamusic_webflux.student.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class AttendanceRouter {

    private final AttendanceService attendanceService;

    @Bean
    public RouterFunction<ServerResponse> attendanceRouterFunction () {
        return route()
                .POST("/attendance", accept(APPLICATION_JSON), this::createAttendance)
                .GET("/attendance/date/{toDate}/student/{id}", accept(APPLICATION_JSON), this::getAttendance)
                .PATCH("/attendance/{id}", accept(APPLICATION_JSON), this::updateAttendance)
                .PUT("/attendance/{studentId}", accept(APPLICATION_JSON), this::initAttendance)
                .build();
    }

    public Mono<ServerResponse> createAttendance(ServerRequest request) {
        log.info("## router start : {}", Thread.currentThread().getName());

        Mono<ServerResponse> result = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(attendanceService.createAttendance(request.bodyToMono(AttendanceDTO.AttendanceReq.class)), Attendance.class);

        log.info("## router end : {}", Thread.currentThread().getName());
        return result;
    }

    public Mono<ServerResponse> getAttendance(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(attendanceService.getAttendance(
                        request.pathVariable("toDate"),
                        request.pathVariable("id")
                ), Attendance.class);
    }

    public Mono<ServerResponse> updateAttendance(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(attendanceService.updateAttendance(
                        request.pathVariable("id"),
                        request.bodyToMono(AttendanceDTO.AttendanceReq.class)
                ), Attendance.class);
    }

    public Mono<ServerResponse> initAttendance(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(attendanceService.initAttendance(request.pathVariable("studentId")), Attendance.class);
    }
}
