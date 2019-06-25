package ru.kazimir.bortnik.controller_module.ExceptionHandlers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "ru.kazimir.bortnik.controller_module.api")
public class GlobalExceptionHandlers {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlers.class);

    @ExceptionHandler({NumberFormatException.class, HttpMessageNotReadableException.class})
    public final ResponseEntity<Object> typeDefinitionError(Exception ex) {
        logger.info("Error:= {} ", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
