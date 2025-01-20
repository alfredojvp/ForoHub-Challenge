package com.fjarar.forohub.controller;

import com.fjarar.forohub.domain.perfil.*;
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
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    /*@Autowired
    private PerfilRepository perfilRepository;*/
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaPerfil> registrarPerfil(
            @RequestBody @Valid DatosRegistroPerfil datosRegistroPerfil,
            UriComponentsBuilder uriComponentsBuilder
            ){
        Perfil perfil = perfilService.registrarPerfil(datosRegistroPerfil);
        DatosRespuestaPerfil datosRespuestaPerfil = new DatosRespuestaPerfil(
                perfil.getId(),
                perfil.getNombre()
        );
        URI url = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPerfil);
    }

    @GetMapping
    @Transactional
    public Page<DatosListadoPerfil> listadoPerfiles(@PageableDefault(page = 0, size = 10, sort = {"nombre"})Pageable paginacion){
        return perfilService.listadoPerfil(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPerfil> actualizarPerfil(
            @RequestBody @Valid DatosActualizarPerfil datosActualizarPerfil){
        Perfil perfil = perfilService.actualizarPerfil(datosActualizarPerfil);
        perfil.actualizarPerfil(datosActualizarPerfil);
        return ResponseEntity.ok(new DatosRespuestaPerfil(
                perfil.getId(),
                perfil.getNombre()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        try{
            perfilService.eliminarPerfil(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        /*if(!perfilRepository.existsById(id)){

        }

        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();*/
    }
}
