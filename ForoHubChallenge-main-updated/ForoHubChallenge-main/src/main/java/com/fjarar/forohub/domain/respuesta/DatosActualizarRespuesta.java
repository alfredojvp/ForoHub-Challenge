package com.fjarar.forohub.domain.respuesta;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosActualizarRespuesta(
        Long id,
        String mensaje,
        Long tema,
        LocalDateTime fechaCreacion,
        Long autor,
        boolean solucion
) {
}
