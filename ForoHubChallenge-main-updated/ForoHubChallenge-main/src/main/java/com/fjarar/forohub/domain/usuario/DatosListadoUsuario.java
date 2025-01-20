package com.fjarar.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String email
) {
    public DatosListadoUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
