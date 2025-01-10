package com.globalL.userServices.exceptions;


import com.globalL.userServices.responses.ExceptionErrorDto;
import com.globalL.userServices.responses.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalHandlerException {

    private ExceptionErrorDto errotDto = new ExceptionErrorDto();

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.BAD_REQUEST.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.BAD_REQUEST.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getFieldErrors().get(0).getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}.", ex.getMessage(), ex);
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.NOT_FOUND.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("BadCredentialsException: {}.", ex.getMessage(), ex);
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.BAD_REQUEST.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException: {}.", ex.getMessage(), ex);
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.BAD_REQUEST.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ExceptionResponseDto<Object>> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);

        errotDto.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errotDto.setTimestamp(new Date());
        errotDto.setDetail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponseDto.builder()
                        .error(errotDto)
                        .build());
    }

}
