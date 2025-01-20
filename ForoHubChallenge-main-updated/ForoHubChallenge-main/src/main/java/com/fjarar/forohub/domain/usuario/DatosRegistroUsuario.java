package com.fjarar.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        @JsonAlias("correo_electronico")
        String email,
        @NotBlank
        String clave
) {
}
