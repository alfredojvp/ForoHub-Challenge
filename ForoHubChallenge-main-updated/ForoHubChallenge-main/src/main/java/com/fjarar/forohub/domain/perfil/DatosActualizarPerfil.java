package com.fjarar.forohub.domain.perfil;

import org.jetbrains.annotations.NotNull;

public record DatosActualizarPerfil(
        @NotNull
        Long id,
        String nombre
) {
}
