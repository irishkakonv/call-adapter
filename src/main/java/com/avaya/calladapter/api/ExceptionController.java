package com.avaya.calladapter.api;

import org.openapitools.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleValidationException(ConstraintViolationException ex) {

        ErrorResponse errorResponse = createErrorResponse();
        errorResponse.message(ex.getConstraintViolations().iterator().next().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        ErrorResponse errorResponse = createErrorResponse();
        FieldError fieldError = ex.getBindingResult().getFieldErrors().iterator().next();
        errorResponse.message(fieldError.getField() + " " + fieldError.getDefaultMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse createErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        errorResponse.details(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.timestamp(OffsetDateTime.now());
        return errorResponse;
    }
}
