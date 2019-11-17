package br.com.curso.controller;

import br.com.curso.model.Curso;
import br.com.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addCurso(@RequestBody Curso curso) {
        this.cursoService.save(curso);
        return new ResponseEntity<String>(curso.toString(), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        this.cursoService.update(id, curso);
        return new ResponseEntity<String>(curso.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable("id") Long id) {
        this.cursoService.delete(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCurso(@PathVariable("id") Long id) {
        Curso curso = null;
        curso = this.cursoService.findById(id);
        return new ResponseEntity<String>(curso.toString(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<String> listAll() {
        List<Curso> cursos = this.cursoService.findAll();
        return new ResponseEntity<String>(cursos.toString(), HttpStatus.OK);
    }
}