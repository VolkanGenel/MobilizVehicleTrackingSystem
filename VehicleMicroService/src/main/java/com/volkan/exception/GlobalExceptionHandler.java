package com.volkan.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.ok("Beklenmeyen bir hata olustu: "+ex.getMessage());
    }
    @ExceptionHandler(VehicleManagerException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(VehicleManagerException ex){
        EErrorType errorType=ex.getErrorType();
        HttpStatus httpStatus=errorType.getHttpStatus();
        return new ResponseEntity<>(createError(errorType,ex),httpStatus);
    }

    private ErrorMessage createError(EErrorType errorType, Exception ex) {
        System.out.println("Hata olustu: "+ex.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        EErrorType errorType=EErrorType.BAD_REQUEST;
        List<String> fields=new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e->fields.add(e.getField()+": "+e.getDefaultMessage()));
        ErrorMessage errorMessage=createError(errorType,exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,errorType.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        EErrorType errorType = EErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        EErrorType errorType = EErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        EErrorType errorType = EErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        EErrorType errorType = EErrorType.BAD_REQUEST;
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ErrorMessage> handlePsqlException(DataIntegrityViolationException exception){
        EErrorType errorType=EErrorType.VEHICLE_DUPLICATE;
        return new ResponseEntity<>(createError(errorType,exception),errorType.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
        EErrorType errorType = EErrorType.INTERNAL_ERROR;
        List<String> fields = new ArrayList<>();
        fields.add(exception.getMessage());
        ErrorMessage errorMessage = createError(errorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(createError(errorType, exception), errorType.getHttpStatus());
    }

}
