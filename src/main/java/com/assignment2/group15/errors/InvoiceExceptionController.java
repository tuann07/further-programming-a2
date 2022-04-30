package com.assignment2.group15.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvoiceExceptionController {

    @ExceptionHandler(value = InvoiceNotExist.class)
    public ResponseEntity<Object> exception(InvoiceNotExist exception) {
        return new ResponseEntity<>("Invoice not found", HttpStatus.NOT_FOUND);
    }

}
