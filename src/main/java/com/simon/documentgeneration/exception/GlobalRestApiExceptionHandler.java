package com.simon.documentgeneration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestApiExceptionHandler {

    @ExceptionHandler(NullParameterInRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerNullParameterInRequest(NullParameterInRequest exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
