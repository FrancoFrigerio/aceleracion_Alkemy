package com.alkemy.java.controller.exception;

import com.alkemy.java.dto.ErrorDataMessageDto;
import com.alkemy.java.dto.ErrorMessageDto;
import com.alkemy.java.exception.EmailNotSentException;
import com.alkemy.java.exception.ResourceNotFoundException;
import com.alkemy.java.exception.*;
import javassist.NotFoundException;
import static com.alkemy.java.util.Constants.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;


@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto usernameNotFoundException(UsernameNotFoundException ex) {
        return new ErrorMessageDto(new Date(), USERNAME_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessageDto.builder()
                .timestamp(new Date())
                .exception(NOT_FOUND)
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorMessageDto(new Date(),"ResourceNotFoundException", ex.getMessage());
    }


    @ExceptionHandler(value = EmailNotSentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto emailNotSendException(EmailNotSentException ex) {
        return new ErrorMessageDto(new Date(), EMAIL_NOT_SENT, ex.getMessage());
    }


    
     @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto BadRequestException(BadRequestException ex) {
        return new ErrorMessageDto(new Date(),BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(value = InvalidDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
      public ErrorDataMessageDto InvalidDataException(InvalidDataException exc) {
    
    List<String> errors = exc.getResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    return new ErrorDataMessageDto (new Date(),INVALID_DATA,errors);
    
      }
      

}