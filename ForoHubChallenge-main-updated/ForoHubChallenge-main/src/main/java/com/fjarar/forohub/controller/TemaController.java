package com.fjarar.forohub.controller;

import com.fjarar.forohub.domain.tema.*;
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
@RequestMapping("/temas")
@SecurityRequirement(name = "bearer-key")
public class TemaController {

    /*@Autowired
    private TemaRepository temaRepository;*/
    private final TemaService temaService;

    public TemaController(TemaService temaService){
        this.temaService = temaService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTema> registrarTema(
            @RequestBody @Valid DatosRegistroTema datosRegistroTema,
            UriComponentsBuilder uriComponentsBuilder
            ){
        Tema tema = temaService.registrarTema(datosRegistroTema);
        DatosRespuestaTema datosRespuestaTema = new DatosRespuestaTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaCreacion(),
                tema.getStatus(),
                tema.getAutor(),
                tema.getCurso()
        );
        URI url = uriComponentsBuilder.path("/temas/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTema);
    }

    @GetMapping
    @Transactional
    public Page<DatosListadoTema> listadoTemas(@PageableDefault(page = 0, size = 10, sort = {"titulo"})Pageable paginacion){
        return temaService.listadoTemas(paginacion);
    };

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTema> actualizarTema(
            @RequestBody @Valid DatosActualizarTema datosActualizarTema){
        Tema tema = temaService.actualizarTema(datosActualizarTema);
        tema.actualizarTema(datosActualizarTema);
        return ResponseEntity.ok(new DatosRespuestaTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaCreacion(),
                tema.getStatus(),
                tema.getAutor(),
                tema.getCurso()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTema(@PathVariable Long id){
        /*if(!temaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        temaRepository.deleteById(id);
        return ResponseEntity.noContent().build();*/
        try {
            temaService.eliminarTema(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
