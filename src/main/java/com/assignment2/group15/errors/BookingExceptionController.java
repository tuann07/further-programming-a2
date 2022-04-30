package com.assignment2.group15.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionController
{
    @ExceptionHandler(value = BookingNotExist.class)
    public ResponseEntity<Object> exception(BookingNotExist exception)
    {
        return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
    }
}
