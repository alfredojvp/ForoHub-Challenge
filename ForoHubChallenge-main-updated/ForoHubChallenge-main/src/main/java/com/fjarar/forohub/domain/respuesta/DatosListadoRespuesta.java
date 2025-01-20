package com.fjarar.forohub.domain.respuesta;

import com.fjarar.forohub.domain.tema.Tema;
import com.fjarar.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosListadoRespuesta(
        Long id,
        String mensaje,
        Tema tema,
        LocalDateTime fechaCreacion,
        Usuario autor,
        boolean solucion
) {
    public DatosListadoRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTema(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor(),
                respuesta.isSolucion()
        );
    }
}
