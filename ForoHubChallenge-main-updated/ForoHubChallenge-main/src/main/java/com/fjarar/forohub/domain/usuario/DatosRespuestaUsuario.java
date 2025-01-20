package com.fjarar.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        @JsonAlias("correo_electronico")
        String email
) {
}
