package com.spring.security.hw11.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // глобальный перехватчик исключений
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler // перехватываем все исключения и отправляем их клиенту в виде джейсон объекта
    public ResponseEntity<AppError> catchResourseNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // перехватываем все исключения и отправляем их клиенту в виде джейсон объекта
    public ResponseEntity<FieldsValidationError> catchValidationException(ValidationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidationError(e.getErrorFieldsMessages()), HttpStatus.BAD_REQUEST);
    }
}
