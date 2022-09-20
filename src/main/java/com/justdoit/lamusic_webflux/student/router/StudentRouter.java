package com.justdoit.lamusic_webflux.student.router;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.service.StudentService;
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
public class StudentRouter {

    private final StudentService studentService;

    @Bean
    public RouterFunction<ServerResponse> studentRouterFunction () {
            return route()
                    .POST("/student", accept(APPLICATION_JSON), this::createStudent)
                    .GET("/student/{id}", accept(APPLICATION_JSON), this::getStudent)
                    .GET("/student", accept(APPLICATION_JSON), this::getAllStudent)
                    .PATCH("/student/{id}", accept(APPLICATION_JSON), this::updateStudent)
            .build();
    }

    public Mono<ServerResponse> createStudent(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.createStudent(request.bodyToMono(StudentDTO.StudentReq.class)), Student.class);
    }

    public Mono<ServerResponse> getStudent(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getStudent(request.pathVariable("id")), Student.class);
    }

    public Mono<ServerResponse> getAllStudent(ServerRequest request) {
        log.info("## router start : {}", Thread.currentThread().getName());

        Mono<ServerResponse> result = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getAllStudent(), Student.class);

        log.info("## router end : {}", Thread.currentThread().getName());

        return result;
    }

    public Mono<ServerResponse> updateStudent(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.updateStudent(
                        request.pathVariable("id"),
                        request.bodyToMono(StudentDTO.StudentReq.class)
                ), Student.class);
    }
}
