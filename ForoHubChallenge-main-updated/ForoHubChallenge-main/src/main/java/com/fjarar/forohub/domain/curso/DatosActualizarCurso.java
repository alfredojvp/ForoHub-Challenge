package com.fjarar.forohub.domain.curso;

import org.jetbrains.annotations.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
