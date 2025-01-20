package com.fjarar.forohub.domain.curso;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fjarar.forohub.domain.tema.Tema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tema> temas = new ArrayList<>();

    /*public Curso(@Valid DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }*/

    public Curso(@NotBlank String nombre, @NotBlank String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public void actualizarCurso(@Valid DatosActualizarCurso datosActualizarCurso) {
        if(datosActualizarCurso.nombre() !=  null){
            this.nombre = datosActualizarCurso.nombre();
        }
        if(datosActualizarCurso.categoria() != null){
            this.categoria = datosActualizarCurso.categoria();
        }
    }
}
