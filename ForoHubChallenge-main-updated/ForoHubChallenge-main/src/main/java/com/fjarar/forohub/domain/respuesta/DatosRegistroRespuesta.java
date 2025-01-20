package com.fjarar.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long tema,
        @NotNull
        Long autor
) {
}
