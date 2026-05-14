package com.colegio.mscurso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class CursoRequestDTO {
    
    private String nombre;
    private String seccion;
    private Integer anio;
}