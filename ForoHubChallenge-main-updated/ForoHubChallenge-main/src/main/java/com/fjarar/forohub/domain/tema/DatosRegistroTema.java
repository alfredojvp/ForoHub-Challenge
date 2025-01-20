package com.fjarar.forohub.domain.tema;

import java.util.Date;

public record DatosRegistroTema(
        String titulo,
        String mensaje,
        String status,
        Long autor,
        Long curso
) {
}
