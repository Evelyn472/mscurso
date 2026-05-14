package com.colegio.mscurso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // <--- ¡ESTA ES LA CRUCIAL! Si falta, te da el error de la foto
public class CursoResponseDTO {
    private Long id;
    private String nombre;
    private String seccion;
    private Integer anio;
}