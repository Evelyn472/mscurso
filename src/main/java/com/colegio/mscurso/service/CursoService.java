package com.colegio.mscurso.service;

import com.colegio.mscurso.dto.CursoRequestDTO;
import com.colegio.mscurso.dto.CursoResponseDTO;
import com.colegio.mscurso.model.Curso;
import com.colegio.mscurso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor 
public class CursoService {

    private final CursoRepository cursoRepository;

    private CursoResponseDTO mapToDTO(Curso c) {
        return new CursoResponseDTO(
                c.getId(), c.getNombre(), c.getSeccion(), c.getAnio());
    }

    public List<CursoResponseDTO> obtenerTodos() {
        return cursoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

   public Optional<CursoResponseDTO> obtenerPorId(Long id) {
        return cursoRepository.findById(id).map(this::mapToDTO);
    }

    public CursoResponseDTO guardar(CursoRequestDTO dto) {
        if (cursoRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe un curso con el nombre: " + dto.getNombre());
        }

        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setSeccion(dto.getSeccion());
        curso.setAnio(dto.getAnio());
        
        Curso cursoGuardado = cursoRepository.save(curso);
        return mapToDTO(cursoGuardado);
    }

    public Optional<CursoResponseDTO> actualizar(Long id, CursoRequestDTO dto) {
        return cursoRepository.findById(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setSeccion(dto.getSeccion());
            existente.setAnio(dto.getAnio());
            
            Curso cursoActualizado = cursoRepository.save(existente);
            return mapToDTO(cursoActualizado);
        });
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}