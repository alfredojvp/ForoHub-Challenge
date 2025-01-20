package com.fjarar.forohub.domain.tema;

import com.fjarar.forohub.domain.curso.Curso;
import com.fjarar.forohub.domain.curso.CursoRepository;
import com.fjarar.forohub.domain.usuario.Usuario;
import com.fjarar.forohub.domain.usuario.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

    private final TemaRepository temaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TemaService(TemaRepository temaRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.temaRepository = temaRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public Tema registrarTema(DatosRegistroTema datosRegistroTema){
        Usuario autor = usuarioRepository.findById(datosRegistroTema.autor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario con ID: "+datosRegistroTema.autor()+" no encontrado"));
        Curso curso = cursoRepository.findById(datosRegistroTema.curso())
                .orElseThrow(() -> new IllegalArgumentException("Curso con ID: "+datosRegistroTema.curso()+" no encontrado"));
        return temaRepository.save(
                new Tema(
                        datosRegistroTema.titulo(),
                        datosRegistroTema.mensaje(),
                        datosRegistroTema.status(),
                        autor, curso));
    }

    public Page<DatosListadoTema> listadoTemas(Pageable paginacion){
        return temaRepository.findAll(paginacion).map(DatosListadoTema::new);
    }

    public Tema actualizarTema(DatosActualizarTema datosActualizarTema){
        Tema tema = temaRepository.getReferenceById(datosActualizarTema.id());
        tema.actualizarTema(datosActualizarTema);
        return tema;
    }

    public void eliminarTema(Long id){
        if(!temaRepository.existsById(id)){
            throw new IllegalArgumentException("Tema no encontrado con id: "+id);
        }
        temaRepository.deleteById(id);
    }
}
