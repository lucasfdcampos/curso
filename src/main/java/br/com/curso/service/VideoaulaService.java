package br.com.curso.service;

import br.com.curso.model.Videoaula;
import br.com.curso.repository.VideoaulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoaulaService {

    @Autowired
    private VideoaulaRepository videoaulaRepository;

    @Autowired
    private CursoService cursoService;

    public VideoaulaService() {
        super();
    }

    public VideoaulaService(VideoaulaRepository videoaulaRepository, CursoService cursoService) {
        super();
        this.videoaulaRepository = videoaulaRepository;
        this.cursoService = cursoService;
    }

    public void save(Long idCurso, Videoaula videoaula) {
        videoaula.setCurso(cursoService.findById(idCurso));
        this.videoaulaRepository.save(videoaula);
    }

    public void update(Long idCurso, Long idVideoaula, Videoaula videoaula) {
        videoaula.setCurso(cursoService.findById(idCurso));
        videoaula.setId(idVideoaula);
        this.videoaulaRepository.save(videoaula);
    }

    public void delete(Long idVideoaula, Long idCurso) {
        this.videoaulaRepository.delete(this.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso));
    }

    @Transactional(readOnly = true)
    public Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
        return this.videoaulaRepository.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
    }

    @Transactional(readOnly = true)
    public List<Videoaula> findAllByCurso(Long idCurso) {
        return this.videoaulaRepository.findAllByCurso(idCurso);
    }
}
