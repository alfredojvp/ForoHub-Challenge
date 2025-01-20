package com.fjarar.forohub.domain.respuesta;

import com.fjarar.forohub.domain.tema.Tema;
import com.fjarar.forohub.domain.tema.TemaRepository;
import com.fjarar.forohub.domain.usuario.Usuario;
import com.fjarar.forohub.domain.usuario.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TemaRepository temaRepository;
    private final UsuarioRepository usuarioRepository;

    public RespuestaService(RespuestaRepository respuestaRepository, TemaRepository temaRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.temaRepository = temaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /*public Respuesta registrarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        Respuesta respuesta = new Respuesta(datosRegistroRespuesta);
        return respuestaRepository.save(respuesta);
    }*/
    public Respuesta registrarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta){
        Tema tema = temaRepository.findById(datosRegistroRespuesta.tema())
                .orElseThrow(() -> new IllegalArgumentException("Tema no encontrado con ID: "+datosRegistroRespuesta.tema()));
        Usuario autor = usuarioRepository.findById(datosRegistroRespuesta.autor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario con ID: "+datosRegistroRespuesta.autor()+" no encontrado"));
        return respuestaRepository.save(
                new Respuesta(
                        datosRegistroRespuesta.mensaje(),
                        tema, autor));
    }

    public Page<DatosListadoRespuesta> listadoRespuestas(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new);
    }

    public Respuesta actualizarRespuesta(DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return respuesta;
    }

    public void eliminarRespuesta(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new IllegalArgumentException("Respuesta no encontrada con id: "+id);
        }
       /* if(!respuestaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        respuestaRepository.deleteById(id);
        return ResponseEntity.noContent().build();*/
        respuestaRepository.deleteById(id);
    }
}
