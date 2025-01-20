package com.fjarar.forohub.domain.tema;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fjarar.forohub.domain.curso.Curso;
import com.fjarar.forohub.domain.respuesta.Respuesta;
import com.fjarar.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "temas")
@Entity(name = "Tema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String status;

    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    @JsonBackReference
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso", nullable = false)
    @JsonBackReference
    private Curso curso;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Respuesta> respuestas = new ArrayList<>();

    public Tema(String titulo, String mensaje, String status, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    /* public Tema(@Valid DatosRegistroTema datosRegistroTema) {
        this.titulo = datosRegistroTema.titulo();
        this.mensaje = datosRegistroTema.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = datosRegistroTema.status();
        this.autor = datosRegistroTema.autor();
        this.curso = datosRegistroTema.curso();
    }*/

    public void actualizarTema(@Valid DatosActualizarTema datosActualizarTema) {
        if(datosActualizarTema.titulo() != null){
            this.titulo = datosActualizarTema.titulo();
        }
        if(datosActualizarTema.mensaje() != null){
            this.mensaje = datosActualizarTema.mensaje();
        }
        if(datosActualizarTema.status() != null){
            this.status = datosActualizarTema.status();
        }
        /*if(datosActualizarTema.curso() != null){
            this.curso = datosActualizarTema.curso();
        }*/
    }
}
