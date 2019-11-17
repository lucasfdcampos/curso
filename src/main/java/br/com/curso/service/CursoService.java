package br.com.curso.service;

import br.com.curso.model.CargaHoraria;
import br.com.curso.model.Curso;
import br.com.curso.model.Videoaula;
import br.com.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CursoService {
    private static final String CURSO_NULL = "Object Curso está nulo";
    private static final String TITULO_NULL = "Titulo está nulo";
    private static final String TITULO_EMPTY = "Titulo está vazio";
    private static final String STATUS_INVALID = "Status inválido";
    private static final String ID_INVALID = "ID inválido";
    private static final String NOT_FOUND = "Curso não encontrado";

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

        if (curso == null) {
            throw new ServiceException(CURSO_NULL);

        } else if (curso.getTitulo() == null) {
            throw new ServiceException(TITULO_NULL);

        } else if (curso.getTitulo().isEmpty()) {
            throw new ServiceException(TITULO_EMPTY);

        } else if (curso.getAtivo() == null) {
            throw new ServiceException(STATUS_INVALID);
        }
        curso.setDataInicio(new Date());
        this.cursoRepository.save(curso);

        if (curso.getVideoaulas() != null) {
            curso.getVideoaulas()
                    .parallelStream()
                    .forEach(curso::addVideoaula);
        }
    }

    public void update(Long id, Curso curso) {

        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ID_INVALID);

        } else if (curso == null) {
            throw new ServiceException(CURSO_NULL);

        } else if ((curso.getTitulo().isEmpty()) || (curso.getTitulo() == null)) {
            throw new ServiceException(TITULO_EMPTY);

        } else if ((curso.getAtivo() == null)) {
            throw new ServiceException(STATUS_INVALID);
        }

        Curso cursoSearch = null;
        try {
            cursoSearch = this.cursoRepository.getOne(id);

            if (cursoSearch == null) {
                throw new ServiceException(NOT_FOUND);
            }

        } catch (EntityNotFoundException e) {
            throw new ServiceException(NOT_FOUND);

        } catch (ServiceException e) {
            throw new ServiceException(NOT_FOUND);
        }
        curso.setId(id);
        curso.setTitulo(curso.getTitulo());
        curso.setDataInicio(new Date());
        curso.setAtivo(curso.getAtivo());
        curso.setCargaHoraria(curso.getCargaHoraria());
        this.cursoRepository.save(curso);
    }

    public void delete(Long id) {

        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ID_INVALID);
        }

        Curso cursoSearch = null;
        try {
            cursoSearch = this.cursoRepository.getOne(id);

            if (cursoSearch == null) {
                throw new ServiceException(NOT_FOUND);
            }

        } catch (EntityNotFoundException e) {
            throw new ServiceException(NOT_FOUND);

        } catch (ServiceException e) {
            throw new ServiceException(NOT_FOUND);
        }

        this.cursoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Curso findById(Long id) {

        if ((id == null) || (id <= 0)) {
            throw new ServiceException(ID_INVALID);
        }

        Curso cursoSearch = null;
        try {
            cursoSearch = this.cursoRepository.getOne(id);

            if (cursoSearch == null) {
                throw new ServiceException(NOT_FOUND);
            }

        } catch (EntityNotFoundException e) {
            throw new ServiceException(NOT_FOUND);

        } catch (ServiceException e) {
            throw new ServiceException(NOT_FOUND);
        }

        return cursoSearch;
    }

    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }
}
