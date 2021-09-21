package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandleException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Exception> handleBadRequest()
    {
        Exception ex = new Exception();
        ex.setStatus(400);
        ex.setMessage("Bad request parameter");
        ex.setError("Bad request");

        return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ResponseEntity<Exception> handleConflict()
    {
        Exception ex = new Exception();
        ex.setStatus(409);
        ex.setMessage("Already exist email");
        ex.setError("Conflict");

        return new ResponseEntity<>(ex,HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Exception> handleInternalServerError()
    {
        Exception ex = new Exception();
        ex.setStatus(409);
        ex.setMessage("Internal server error");
        ex.setError("Internal server error");

        return new ResponseEntity<>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
