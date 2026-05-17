package com.colegio.mscurso.dto;

public class CursoRequestDTO {
    private String nombre;
    private String seccion;
    private Long profesorId;

    public CursoRequestDTO() {}

    public CursoRequestDTO(String nombre, String seccion, Long profesorId) {
        this.nombre = nombre;
        this.seccion = seccion;
        this.profesorId = profesorId;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }

    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }
}