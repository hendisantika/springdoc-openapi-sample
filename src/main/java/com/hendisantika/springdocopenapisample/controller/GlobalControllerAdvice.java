package com.hendisantika.springdocopenapisample.controller;

import com.hendisantika.springdocopenapisample.model.ErrorMessage;
import com.hendisantika.springdocopenapisample.model.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-openapi-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 26/04/20
 * Time: 15.39
 */
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice {
    /*
     * Note use base class if you wish to leverage its handling.
     * Some code will need changing.
     * */

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Problem> problem(final Throwable e) {
        String message = e.getMessage();
        //might actually prefer to use a generic message
        message = "Problem occured";
        UUID uuid = UUID.randomUUID();
        String logRef = uuid.toString();
        logger.error("logRef=" + logRef, message, e);
        return new ResponseEntity<Problem>(new Problem(logRef, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }

        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorMessage errorMessage = new ErrorMessage(errors);
        //Object result=ex.getBindingResult();//instead of above can allso pass the more detailed bindingResult
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);

    }
}
