package com.fjarar.forohub.controller;

import com.fjarar.forohub.domain.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(
            @RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
            UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.registrarUsuario(datosRegistroUsuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    @Transactional
    public Page<DatosListadoUsuario> listadoUsuarios(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return usuarioService.listadoUsuario(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(
            @RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioService.actualizarUsuario(datosActualizarUsuario);
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try{
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        /*Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();*/
    }
}
