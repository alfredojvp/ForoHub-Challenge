package com.fjarar.forohub.domain.respuesta;

import com.fjarar.forohub.domain.tema.Tema;
import com.fjarar.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosRespuesta(
        Long id,
        String mensaje,
        Tema tema,
        LocalDateTime fechaCreacion,
        Usuario autor
) {
}
