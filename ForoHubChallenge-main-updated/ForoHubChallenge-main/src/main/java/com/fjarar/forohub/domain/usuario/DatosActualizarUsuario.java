package com.fjarar.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;

public record DatosActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        @Email
        String email,
        String clave
) {
}
