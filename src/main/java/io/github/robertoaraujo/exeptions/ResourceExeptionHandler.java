package io.github.robertoaraujo.exeptions;

import io.github.robertoaraujo.service.exeptions.AuthorizationException;
import io.github.robertoaraujo.service.exeptions.BadRequestException;
import io.github.robertoaraujo.service.exeptions.OjetoNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExeptionHandler {

    @ExceptionHandler(OjetoNotFoundExeption.class)
    public ResponseEntity<StandarError> objectNotFound(OjetoNotFoundExeption e, HttpServletRequest request) {
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandarError> authorization(AuthorizationException e, HttpServletRequest request) {
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandarError> badRequest(BadRequestException e, HttpServletRequest request) {
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandarError> InternalServerError(RuntimeException e, HttpServletRequest request) {
        StandarError error = new StandarError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
