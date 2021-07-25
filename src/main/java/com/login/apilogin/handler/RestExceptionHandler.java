package com.login.apilogin.handler;

import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.exception.details.BadRequestDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
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
}
