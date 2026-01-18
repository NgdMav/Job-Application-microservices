package com.mav.microservicecompany.web;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
class GlobalControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            Exception e
    ) {
        log.error(e.getMessage(), e);
        var errorDto = new ErrorResponseDTO(
                "Internal Server Error",
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
            NoSuchElementException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(
            Exception e
    ) {
        log.error(e.getMessage(), e);
        var errorDto = new ErrorResponseDTO(
                "Entity not found",
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = {
            IllegalStateException.class,
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(
            Exception e
    ) {
        log.error(e.getMessage(), e);
        var errorDto = new ErrorResponseDTO(
                "Bad Request",
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
