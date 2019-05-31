package com.foodtruck.controller;

import com.foodtruck.exception.LocationServiceException;
import com.foodtruck.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.foodtruck.constant.ApiConstant.SERVER_ERROR_MESSAGE;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Exception while processing the request ", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),SERVER_ERROR_MESSAGE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException e) {
        log.error("Invalid request ", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder strBuilder = new StringBuilder("Invalid fields : ");
        List<String> invalidFields = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            invalidFields.add(fieldError.getField());
        });
        strBuilder.append(invalidFields);
                ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                strBuilder.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationServiceException.class)
    public ResponseEntity<ExceptionResponse> handleLocationServiceException(LocationServiceException e){
        log.error("Error while calling location service ",e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<ExceptionResponse> handleHttpClientException(HttpClientErrorException ex) {
        ExceptionResponse exceptionResponse;
        HttpStatus status;
        log.error("handleHttpClientException Exception :{}", ex);
        if (ex.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()) {
            exceptionResponse = new ExceptionResponse(ResponseCodeEnum.BAD_REQUEST.getResponseCode(), ex.getMessage());
            status = HttpStatus.BAD_REQUEST;
        } else {
            exceptionResponse = new ExceptionResponse(ResponseCodeEnum.SERVER_ERROR.getResponseCode(), ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(exceptionResponse, status);
    }*/
}
