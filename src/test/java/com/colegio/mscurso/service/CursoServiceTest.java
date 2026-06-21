package com.colegio.mscurso.service;

import com.colegio.mscurso.client.ProfesorClient;
import com.colegio.mscurso.dto.CursoRequestDTO;
import com.colegio.mscurso.dto.CursoResponseDTO;
import com.colegio.mscurso.model.Curso;
import com.colegio.mscurso.repository.CursoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProfesorClient profesorClient;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void guardarCursoCorrectamente() {

        CursoRequestDTO dto = new CursoRequestDTO(
                "4 Medio A",
                "A",
                1L
        );

        Curso cursoGuardado = new Curso(
                1L,
                "4 Medio A",
                "A",
                1L
        );

        when(profesorClient.obtenerPorId(1L))
                .thenReturn(new Object());

        when(cursoRepository.save(any(Curso.class)))
                .thenReturn(cursoGuardado);

        CursoResponseDTO resultado = cursoService.guardar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("4 Medio A", resultado.getNombre());

        verify(cursoRepository, times(1))
                .save(any(Curso.class));
    }

    @Test
    void obtenerCursoPorId() {

        Curso curso = new Curso(
                1L,
                "4 Medio A",
                "A",
                1L
        );

        when(cursoRepository.findById(1L))
                .thenReturn(Optional.of(curso));

        Optional<CursoResponseDTO> resultado =
                cursoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("4 Medio A",
                resultado.get().getNombre());
    }

    @Test
    void eliminarCurso() {

        doNothing().when(cursoRepository)
                .deleteById(1L);

        cursoService.eliminar(1L);

        verify(cursoRepository, times(1))
                .deleteById(1L);
    }
}