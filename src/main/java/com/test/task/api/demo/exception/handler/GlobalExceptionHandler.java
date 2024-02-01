package com.test.task.api.demo.exception.handler;

import com.test.task.api.demo.exception.NotFoundException;
import com.test.task.api.demo.exception.SlotOccupiedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    @ExceptionHandler(SlotOccupiedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleSlotOccupiedException(SlotOccupiedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot is already occupied");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
}
