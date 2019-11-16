package br.com.curso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    public Curso() {
        super();
    }

    public Curso(Long id, @NotNull String titulo, @NotNull Date dataInicio, CargaHoraria cargaHoraria) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.cargaHoraria = cargaHoraria;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "titulo")
    private String titulo;

    @JsonIgnore
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Column(nullable = false, name = "carga_horaria")
    @Enumerated(EnumType.STRING)
    private CargaHoraria cargaHoraria;

    @Column(nullable = false, name = "ativo")
    private Boolean ativo;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Videoaula> videoaulas;

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public CargaHoraria getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(CargaHoraria cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Boolean getActive() {
        return ativo;
    }

    public void setActive(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Videoaula> getVideoaulas() {
        return videoaulas;
    }

    public void setVideoaulas(List<Videoaula> videoaulas) {
        this.videoaulas = videoaulas;
    }

    public void addVideoaula(Videoaula videoaula) {
        if (this.videoaulas == null) {
            this.videoaulas = new ArrayList<>();
        }
        videoaula.setCurso(this);
        this.videoaulas.add(videoaula);
    }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", titulo='" + titulo + ", dataInicio=" + dataInicio +
                ", cargaHoraria=" + cargaHoraria + ", active=" + ativo + ", videoaulas=" + videoaulas + "]";
    }
}
