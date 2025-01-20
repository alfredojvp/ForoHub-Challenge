package com.fjarar.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fjarar.forohub.domain.tema.Tema;
import com.fjarar.forohub.domain.tema.TemaRepository;
import com.fjarar.forohub.domain.usuario.Usuario;
import com.fjarar.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "tema", nullable = false)
    //private Long tema;
    @JsonBackReference
    private Tema tema;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    @JsonBackReference
    private Usuario autor;
    private boolean solucion = false;

    /*public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
    }*/

    public Respuesta(String mensaje, Tema tema, Usuario autor){
        this.mensaje = mensaje;
        this.tema = tema;
        this.autor = autor;
    }
/*
    public Respuesta(@Valid DatosRegistroRespuesta datosRegistroRespuesta,
                     TemaRepository temaRepository,
                     UsuarioRepository usuarioRepository) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.tema = temaRepository.findById(datosRegistroRespuesta.tema())
                .orElseThrow(() -> new IllegalArgumentException("Tema no encontrado con ID: "+datosRegistroRespuesta.tema()));
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuarioRepository.findById(datosRegistroRespuesta.autor())
                .orElseThrow(() -> new IllegalArgumentException(("Usuario no encontrado con ID: "+datosRegistroRespuesta.autor())));
    }
    */
    @PrePersist
    public void prePersist(){
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarDatos(@Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        if(datosActualizarRespuesta.solucion()){
            this.solucion = true;
        }
        if(datosActualizarRespuesta.mensaje() != null){
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
    }
}
