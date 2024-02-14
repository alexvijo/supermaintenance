package com.w2m.supermaintenance.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.w2m.supermaintenance.exception.HeroNotFoundException;
import com.w2m.supermaintenance.models.CustomError;
import java.util.Date;

@RestControllerAdvice
public class CentralizedExceptionHandlingController {
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleException(Exception ex, HttpServletRequest request) {
        return new CustomError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getClass().getName(),
            ex.getMessage(),
            request.getRequestURI(),
            new Date()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomError handleJsonParseException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return new CustomError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().getName(),
                "Solicitud JSON malformada. Por favor, verifica la sintaxis de tu JSON.",
                request.getRequestURI(),
                new Date());
    }

    @ExceptionHandler(HeroNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CustomError handleHeroNotFoundException(HeroNotFoundException ex, HttpServletRequest request) {
        return new CustomError(
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getName(),
                ex.getMessage(),
                request.getRequestURI(),
                new Date());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        return new CustomError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().getName(),
                "Variable de ruta inválida. Tipo esperado: " + ex.getRequiredType().getName(),
                request.getRequestURI(),
                new Date());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CustomError handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        return new CustomError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                ex.getClass().getName(),
                "Content Type inválido. Tipo esperado:: " + ex.getSupportedMediaTypes(),
                request.getRequestURI(),
                new Date());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
        return new CustomError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getName(),
                "Ocurrió un error inesperado. Por favor, inténtalo de nuevo más tarde.",
                request.getRequestURI(),
                new Date());
    }
}
