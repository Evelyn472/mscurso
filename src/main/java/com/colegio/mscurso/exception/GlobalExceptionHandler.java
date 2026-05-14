package com.colegio.mscurso.exception; // <-- Cambiado para tu paquete de cursos

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Atrapa los errores de validación de los DTOs (@NotBlank, @NotNull, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        
        // Recorre campo por campo buscando qué falló y extrae tu mensaje personalizado
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));
            
        return ResponseEntity.badRequest().body(errores);
    }

    // 2. Atrapa los errores que tú lances a mano en el Service (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage()); // Captura el mensaje que pusiste en el 'throw new RuntimeException'
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}