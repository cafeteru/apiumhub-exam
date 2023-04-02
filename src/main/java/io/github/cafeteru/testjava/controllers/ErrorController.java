package io.github.cafeteru.testjava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.cafeteru.testjava.model.records.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> catchIllegalArgumentException(IllegalArgumentException exception) {
        var error = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
