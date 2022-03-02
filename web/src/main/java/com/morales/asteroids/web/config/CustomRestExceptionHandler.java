package com.morales.asteroids.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import com.morales.asteroids.web.dto.ApiError;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<String> errors = new ArrayList<>();
                ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
                ex.getBindingResult().getGlobalErrors().forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));
                
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
                return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
        }
        
        @Override
        protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
                String error = ex.getParameterName() + " parameter is missing";
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Collections.singletonList(error));
                return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }
        
        @ExceptionHandler({ ConstraintViolationException.class })
        public ResponseEntity<Object> handleException(ConstraintViolationException ex, WebRequest request) {
                List<String> errors = new ArrayList<String>();
                ex.getConstraintViolations().forEach(violation -> errors.add(violation.getPropertyPath() + ": " + violation.getMessage()));
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
                return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
        public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
                String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Collections.singletonList(error));
                return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }
}