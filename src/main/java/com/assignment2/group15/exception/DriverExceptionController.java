package com.assignment2.group15.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DriverExceptionController {

    @ExceptionHandler(value = DriverNotExist.class)
    public ResponseEntity<Object> exception(DriverNotExist exception) {
        return new ResponseEntity<>("Driver not found", HttpStatus.NOT_FOUND);
    }

}
