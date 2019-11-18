package br.com.curso.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "videoaulas")
public class Videoaula implements Serializable {

    private static final long serialVersionUID = 1L;

    public Videoaula() {
        super();
    }

    public Videoaula(Long id, @NotNull @Size(max = 60) String titulo, @NotNull Integer numero, Curso curso) {
        this.id = id;
        this.titulo = titulo;
        this.numero = numero;
        this.curso = curso;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(nullable = false, name = "titulo")
    private String titulo;

    @NotNull
    @Column(nullable = false, name = "descricao")
    private String descricao;

    @NotNull
    @Column(nullable = false, name = "numero")
    private Integer numero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"videoaulas", "titulo", "cargaHoraria", "ativo"})
    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Videoaula [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", numero=" + numero + "]";
    }
}
