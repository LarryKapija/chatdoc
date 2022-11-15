package com.amaury.chatdoc.controllers

import com.amaury.chatdoc.controllers.model.EmptyResponse
import com.amaury.chatdoc.exception.InvalidRequest
import com.amaury.chatdoc.exception.NotFound
import com.amaury.chatdoc.exception.Unauthorized
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import java.util.logging.Logger

@ControllerAdvice
class ExceptionController {
    static Logger log = Logger.getLogger(ExceptionController.class.getName());

    @ExceptionHandler(value = NotFound.class)
    ResponseEntity<EmptyResponse> exception(NotFound exception) {
        log.info(exception.getMessage())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: exception.getMessage()), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = InvalidRequest.class)
    ResponseEntity<EmptyResponse> exception(InvalidRequest exception) {
        log.info(exception.getMessage())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: exception.getMessage()), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = Unauthorized.class)
    ResponseEntity<EmptyResponse> exception(Unauthorized exception) {
        log.info(exception.getMessage())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: exception.getMessage()), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<EmptyResponse> exception(RuntimeException exception) {
        log.info(exception.getMessage())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = IOException.class)
    ResponseEntity<EmptyResponse> exception(IOException exception) {
        log.info(exception.getMessage())
        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
