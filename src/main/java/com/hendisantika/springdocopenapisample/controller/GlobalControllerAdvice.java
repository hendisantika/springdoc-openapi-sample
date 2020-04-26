package com.hendisantika.springdocopenapisample.controller;

import com.hendisantika.springdocopenapisample.model.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        //might actually prefer to use a geeric message
        message = "Problem occured";
        UUID uuid = UUID.randomUUID();
        String logRef = uuid.toString();
        logger.error("logRef=" + logRef, message, e);
        return new ResponseEntity<Problem>(new Problem(logRef, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
