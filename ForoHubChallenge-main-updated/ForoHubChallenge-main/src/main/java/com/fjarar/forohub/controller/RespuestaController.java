package com.fjarar.forohub.controller;

import com.fjarar.forohub.domain.respuesta.*;
import com.fjarar.forohub.domain.tema.TemaRepository;
import com.fjarar.forohub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    //@Autowired
    /*private final RespuestaRepository respuestaRepository;
    private final TemaRepository temaRepository;
    private final UsuarioRepository usuarioRepository;*/
    private final RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService){
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DatosRespuesta> registrarRespuesta(
            @RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
            UriComponentsBuilder uriComponentsBuilder
            ){
        //Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        Respuesta respuesta = respuestaService.registrarRespuesta(datosRegistroRespuesta);

        DatosRespuesta datosRespuesta = new DatosRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTema(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor()
        );
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    @Transactional
    public Page<DatosListadoRespuesta> listadoRespuestas(@PageableDefault(page = 0, size = 10, sort = {"tema"})Pageable paginacion){
        return respuestaService.listadoRespuestas(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaService.actualizarRespuesta(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTema(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id){
        try {
            respuestaService.eliminarRespuesta(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
