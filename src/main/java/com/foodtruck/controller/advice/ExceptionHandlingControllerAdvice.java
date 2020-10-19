package com.foodtruck.controller.advice;

import com.foodtruck.exception.FoodTruckException;
import com.foodtruck.exception.LocationServiceException;
import com.foodtruck.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(FoodTruckException.class)
    public ResponseEntity<ExceptionResponse> handleFoodTruckException(FoodTruckException ex) {
        log.error("handleUnnatiLoanProcessException Exception :{}", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationServiceException.class)
    public ResponseEntity<ExceptionResponse> handleLocationServiceException(LocationServiceException e){
        log.error("Error while calling location service ",e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
