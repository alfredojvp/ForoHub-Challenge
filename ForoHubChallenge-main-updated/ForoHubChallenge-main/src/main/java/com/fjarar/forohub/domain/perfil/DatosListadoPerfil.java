package com.fjarar.forohub.domain.perfil;

public record DatosListadoPerfil(
        String nombre
) {
    public DatosListadoPerfil(Perfil perfil){
        this(perfil.getNombre());
    }
}
