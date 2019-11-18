package br.com.curso.controller;

import br.com.curso.model.Videoaula;
import br.com.curso.service.VideoaulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cursos/{idCurso}/videoaulas")
public class VideoaulaController {

    @Autowired
    private VideoaulaService videoaulaService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addVideoaula(@PathVariable("idCurso") Long idCurso,
                                               @RequestBody Videoaula videoaula) {
        this.videoaulaService.save(idCurso, videoaula);
        return new ResponseEntity<String>(videoaula.toString(), HttpStatus.OK);
    }

    @PutMapping("/update/{idVideoaula}")
    public ResponseEntity<String> updateVideoaula(@PathVariable("idCurso") Long idCurso,
                                                  @PathVariable("idVideoaula") Long idVideoaula,
                                                  @RequestBody Videoaula videoaula) {
        this.videoaulaService.update(idCurso, idVideoaula, videoaula);
        return new ResponseEntity<String>(videoaula.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{idVideoaula}")
    public ResponseEntity<String> deleteVideoaula(@PathVariable("idCurso") Long idCurso,
                                                  @PathVariable("idVideoaula") Long idVideoaula) {
        this.videoaulaService.delete(idVideoaula, idCurso);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @GetMapping("{idVideoaula}")
    public ResponseEntity<String> getVideoaula(@PathVariable("idCurso") Long idCurso,
                                               @PathVariable("idVideoaula") Long idVideoaula) {
        Videoaula videoaula = null;
        videoaula = this.videoaulaService.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
        return new ResponseEntity<String>(videoaula.toString(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<String> listAll(@PathVariable("idCurso") Long idCurso) {
        List<Videoaula> videoaulas = this.videoaulaService.findAllByCurso(idCurso);
        return new ResponseEntity<String>(videoaulas.toString(), HttpStatus.OK);
    }

    @GetMapping()
    public List<Videoaula> listAllEntity(@PathVariable("idCurso") Long idCurso) {
        return this.videoaulaService.findAllByCurso(idCurso);
    }
}
