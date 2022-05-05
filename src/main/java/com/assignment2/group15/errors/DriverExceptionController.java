package com.assignment2.group15.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class DriverExceptionController {
    @ExceptionHandler(value = InvoiceNotExist.class)
    public ResponseEntity<Object> exception(InvoiceNotExist exception) {
        return new ResponseEntity<>("Driver not found", HttpStatus.NOT_FOUND);
    }
}
