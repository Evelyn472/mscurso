package com.colegio.mscurso.service;

import com.colegio.mscurso.client.ProfesorClient;
import com.colegio.mscurso.dto.CursoRequestDTO;
import com.colegio.mscurso.dto.CursoResponseDTO;
import com.colegio.mscurso.model.Curso;
import com.colegio.mscurso.repository.CursoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    private final ProfesorClient profesorClient;

    private CursoResponseDTO mapToDTO(Curso c) {
        return new CursoResponseDTO(
                c.getId(), c.getNombre(), c.getSeccion(), c.getProfesorId());
    }

    private void validarProfesorJefe(Long profesorId) {
        if (profesorId == null) return; 

        try {
            profesorClient.obtenerPorId(profesorId);
            log.info(">>> Profesor Jefe {} validado correctamente (FeignClient)", profesorId);

        } catch (FeignException.NotFound e) {
            throw new RuntimeException(
                    "El profesor jefe con id " + profesorId + " no existe en ms-profesores.");
        } catch (FeignException e) {
            throw new RuntimeException(
                    "No se puede conectar con ms-profesores: " + e.getMessage());
        }
    }

    public List<CursoResponseDTO> obtenerTodos() {
        return cursoRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<CursoResponseDTO> obtenerPorId(Long id) {
        return cursoRepository.findById(id).map(this::mapToDTO);
    }

    public CursoResponseDTO guardar(CursoRequestDTO dto) {
        validarProfesorJefe(dto.getProfesorId());
        
        Curso c = new Curso(null, dto.getNombre(), dto.getSeccion(), dto.getProfesorId());
        return mapToDTO(cursoRepository.save(c));
    }

    public Optional<CursoResponseDTO> actualizar(Long id, CursoRequestDTO dto) {
        return cursoRepository.findById(id).map(existente -> {
            validarProfesorJefe(dto.getProfesorId());
            
            existente.setNombre(dto.getNombre());
            existente.setSeccion(dto.getSeccion());
            existente.setProfesorId(dto.getProfesorId());
            return mapToDTO(cursoRepository.save(existente));
        });
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}