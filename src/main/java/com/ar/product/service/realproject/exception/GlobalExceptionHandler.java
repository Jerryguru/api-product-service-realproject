package com.ar.product.service.realproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice   // is used for
public class GlobalExceptionHandler {
    // here we Handle Product  Not Found Exception
@ExceptionHandler(ProductNotFoundException.class)
public ResponseEntity<ErrorResponse> handleProductNotFoundException
                                                (ProductNotFoundException e){
   // log.error("Exception occurred :{}",e.getMessage());
    ErrorResponse response = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Not Found")
            .message(e.getMessage())
            .build();

    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

}
}
