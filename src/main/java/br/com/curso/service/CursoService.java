package br.com.curso.service;

import br.com.curso.model.Curso;
import br.com.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoService() {
        super();
    }

    public CursoService(CursoRepository cursoRepository) {
        super();
        this.cursoRepository = cursoRepository;
    }

    public void save(Curso curso) {
        this.cursoRepository.save(curso);
    }

    public void update(Long id, Curso curso) {
        curso.setId(id);
        this.cursoRepository.save(curso);
    }

    public void delete(Curso curso) {
        this.cursoRepository.delete(curso);
    }

    public void delete(Long id) {
        this.cursoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Curso findById(Long id) {
        return this.cursoRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }
}
