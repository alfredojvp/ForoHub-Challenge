package com.fjarar.forohub.domain.perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public Perfil registrarPerfil(DatosRegistroPerfil datosRegistroPerfil){
        return perfilRepository.save(
                new Perfil(
                        datosRegistroPerfil.nombre()
                ));
    }

    public Page<DatosListadoPerfil> listadoPerfil(Pageable paginacion){
        return perfilRepository.findAll(paginacion).map(DatosListadoPerfil::new);
    }

    public Perfil actualizarPerfil(DatosActualizarPerfil datosActualizarPerfil){
        Perfil perfil = perfilRepository.getReferenceById(datosActualizarPerfil.id());
        perfil.actualizarPerfil(datosActualizarPerfil);
        return perfil;
    }

    public void eliminarPerfil(Long id){
        if(!perfilRepository.existsById(id)){
            throw new IllegalArgumentException("Perfil no encontrado con ID: "+id);
        }
        perfilRepository.deleteById(id);
    }
}
