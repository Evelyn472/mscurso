package com.colegio.mscurso.dto;

public class CursoResponseDTO {
    private Long id;
    private String nombre;
    private String seccion;
    private Long profesorId;

    public CursoResponseDTO() {}

    public CursoResponseDTO(Long id, String nombre, String seccion, Long profesorId) {
        this.id = id;
        this.nombre = nombre;
        this.seccion = seccion;
        this.profesorId = profesorId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }

    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }
}