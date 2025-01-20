package com.fjarar.forohub.domain.tema;

import com.fjarar.forohub.domain.curso.Curso;
import com.fjarar.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTema(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Usuario autor,
        Curso curso
) {
    public DatosListadoTema(Tema tema){
        this(
                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getFechaCreacion(),
                tema.getStatus(),
                tema.getAutor(),
                tema.getCurso()
        );
    }
}
