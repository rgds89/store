package com.store.resources.exceptions;

import com.store.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourcesNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resources not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stderr = new StandardError(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC), Long.valueOf(status.value()), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stderr);
    }
}
