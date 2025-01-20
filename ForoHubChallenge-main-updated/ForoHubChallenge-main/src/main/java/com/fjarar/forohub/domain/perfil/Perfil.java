package com.fjarar.forohub.domain.perfil;

import com.fjarar.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "perfiles")
    private Set<Usuario> usuarios = new HashSet<>();

    /*public Perfil(@Valid DatosRegistroPerfil datosRegistroPerfil) {
        this.nombre = datosRegistroPerfil.nombre();
    }*/

    public Perfil(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public void actualizarPerfil(@Valid DatosActualizarPerfil datosActualizarPerfil) {
        if(datosActualizarPerfil.nombre() != null){
            this.nombre = datosActualizarPerfil.nombre();
        }
    }
}
