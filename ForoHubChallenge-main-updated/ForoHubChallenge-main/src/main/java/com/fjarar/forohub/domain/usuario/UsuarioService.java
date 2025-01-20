package com.fjarar.forohub.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistroUsuario){
        return usuarioRepository.save(
                new Usuario(
                        datosRegistroUsuario.nombre(),
                        datosRegistroUsuario.email(),
                        datosRegistroUsuario.clave()
                ));
    }

    public Page<DatosListadoUsuario> listadoUsuario(Pageable paginacion){
        return usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
    }

    public Usuario actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        return usuario;
    }

    public void eliminarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new IllegalArgumentException("Usuario no encontrado con ID: "+id);
        }
        usuarioRepository.deleteById(id);
    }
}
