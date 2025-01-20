package com.fjarar.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
