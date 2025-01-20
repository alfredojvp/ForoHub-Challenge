package com.fjarar.forohub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso registrarCurso(DatosRegistroCurso datosRegistroCurso){
        return cursoRepository.save(
                new Curso(
                        datosRegistroCurso.nombre(),
                        datosRegistroCurso.categoria()
                ));
    }

    public Page<DatosListadoCurso> listadoCursos(Pageable paginacion){
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }

    public Curso actualizarCurso(DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarCurso(datosActualizarCurso);
        return curso;
    }

    public void eliminarCurso(Long id){
        if(!cursoRepository.existsById(id)){
            throw new IllegalArgumentException("Curso no encontrado con ID: "+id);
        }
        cursoRepository.deleteById(id);
    }

}
