package org.elis.softwareprenotazioneeventi.exception.gestori;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.elis.softwareprenotazioneeventi.DTO.response.MessaggioErroreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessaggioErroreDTO> methodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        Map<String, String> map = new TreeMap<>();
        for(FieldError o: e.getFieldErrors())
        {
            map.put(o.getField(), o.getDefaultMessage());
        }
        MessaggioErroreDTO m = new MessaggioErroreDTO();
        m.setData(LocalDateTime.now());
        m.setErrori(map);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessaggioErroreDTO> validationException(ConstraintViolationException e)
    {
        Map<String, String> map = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(c->c.getPropertyPath().toString(), ConstraintViolation::getMessage));
        MessaggioErroreDTO m = new MessaggioErroreDTO();
        m.setData(LocalDateTime.now());
        map.remove("idRichiedente");
        m.setErrori(map);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);

    }



}
