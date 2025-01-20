package com.fjarar.forohub.domain.tema;

import org.jetbrains.annotations.NotNull;

public record DatosActualizarTema(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String status,
        Long autor,
        Long curso
) {
}
