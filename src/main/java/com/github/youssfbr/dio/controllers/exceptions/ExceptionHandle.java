package com.github.youssfbr.dio.controllers.exceptions;

import com.github.youssfbr.dio.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException (
            ResourceNotFoundException e,
            HttpServletRequest request) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;

        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(notFound.value())
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(notFound).body(error);
    }

}
