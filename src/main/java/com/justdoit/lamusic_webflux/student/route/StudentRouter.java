package com.justdoit.lamusic_webflux.student.route;

import com.justdoit.lamusic_webflux.student.dto.StudentDTO;
import com.justdoit.lamusic_webflux.student.entity.Student;
import com.justdoit.lamusic_webflux.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@RequiredArgsConstructor
@Configuration
public class StudentRouter {

    private final StudentService studentService;

    @Bean
    public RouterFunction<ServerResponse> studentRouterFunction () {
            return route()
                    .GET("/student/{id}", accept(APPLICATION_JSON), this::getStudent)
                    .POST("/student", accept(APPLICATION_JSON), this::createStudent)
            .build();
    }

    public Mono<ServerResponse> getStudent(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getStudent(request.pathVariable("id")), Student.class);
    }

    public Mono<ServerResponse> createStudent(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(studentService.createStudent(request.bodyToMono(StudentDTO.StudentReq.class)), Student.class);
    }
}
