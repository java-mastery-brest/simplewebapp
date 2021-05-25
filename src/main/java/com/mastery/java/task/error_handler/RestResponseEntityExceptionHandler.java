package com.mastery.java.task.error_handler;

import com.mastery.java.task.exeption.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> mapNEmployeeNotFoundException(EmployeeNotFoundException e) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "Resource Not Found",
                e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> mapDataAccessException(DataAccessException e) {
        ApiError apiError = e.getClass().equals(DataIntegrityViolationException.class) ?
                  new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Constraints Violation",
                          "Cannot Insert Invalid Data")
                : new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Data already exists",
                    e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException e, WebRequest request) {
        if (e.getMessage().contains("No enum constant com.mastery.java.task.model.Gender.")) {
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Invalid gender",
                    "Gender has to be either MALE or FEMALE");

            return ResponseEntity
                    .badRequest()
                    .body(apiError);
        }

        return handleAll(e, request);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(violation.getMessage() + ": " + violation.getInvalidValue());
        }

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Constraint Violations",
                errors);

        return ResponseEntity
                .badRequest()
                .body(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                errors);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Malformed JSON Request" ,
                "Passed JSON Cannot Be Read");

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Type Mismatch",
                    "Wrong " + ((MethodArgumentTypeMismatchException) ex).getName() + " parameter.");

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType()).
                append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        int index = builder.lastIndexOf(", ");
        if (index != -1) {
            builder.delete(index, builder.length());
        }

        ApiError apiError = new ApiError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported Media Type" ,
                Collections.singletonList(builder.toString()));

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(request.getHeader("accept"))
                .append(" media type is not supported. Supported media types are: ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        int index = builder.lastIndexOf(", ");
        if (index != -1) {
            builder.delete(index, builder.length());
        }

        ApiError apiError = new ApiError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported Media Type" ,
                Collections.singletonList(builder.toString()));

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "Request Method Not Supported",
                Collections.singletonList(String.format("Could not find the %s method",
                        ex.getMethod())));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "Method Not Found",
                Collections.singletonList(String.format("Could not find the %s method for URL %s",
                        ex.getHttpMethod(), ex.getRequestURL())));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }


    @ExceptionHandler(Exception.class )
    public ResponseEntity<Object> handleAll(
            Exception ex,
            WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error" ,
                Collections.singletonList(ex.getMessage()));

        log.error("", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }
}
