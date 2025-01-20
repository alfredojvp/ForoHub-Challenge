package com.fjarar.forohub.controller;

import com.fjarar.forohub.domain.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    /*@Autowired
    private CursoRepository cursoRepository;*/
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(
            @RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
            UriComponentsBuilder uriComponentsBuilder
            ){
        Curso curso = cursoService.registrarCurso(datosRegistroCurso);
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    @Transactional
    public Page<DatosListadoCurso> listadoCursos(@PageableDefault(page = 0, size = 10, sort = {"nombre"})Pageable paginacion){
        return cursoService.listadoCursos(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> actualizarCurso(
            @RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoService.actualizarCurso(datosActualizarCurso);
        curso.actualizarCurso(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id){
        try{
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        /*if(!cursoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();*/
    }
}
