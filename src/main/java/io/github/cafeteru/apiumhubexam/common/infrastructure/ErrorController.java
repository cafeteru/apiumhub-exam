package io.github.cafeteru.apiumhubexam.common.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.cafeteru.apiumhubexam.common.domain.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> catchIllegalArgumentException(IllegalArgumentException exception) {
        var error = ErrorDto.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
