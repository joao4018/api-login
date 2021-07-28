package com.login.apilogin.handler;

import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.exception.details.BadRequestDetails;
import com.login.apilogin.exception.details.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestDetails> handlerBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .developerMessage(badRequestException.getClass().getName())
                        .title("Bad Request Exception, Check the Documentation.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(badRequestException.getMessage())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerBadRequestException(
            MethodArgumentNotValidException methodArgumentNotValidException){
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .developerMessage(methodArgumentNotValidException.getClass().getName())
                        .title("Bad Request Exception, Invalid Fields.")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details("Check the field errors!")
                        .fieldsMessage(fieldsMessage)
                        .fields(fields)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
