package br.com.curso.repository;

import br.com.curso.model.Videoaula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoaulaRepository extends JpaRepository<Videoaula, Long> {

    @Query("select v from Videoaula v where v.id = ?1 and v.curso.id = ?1")
    Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso);

    @Query("select v from Videoaula v where v.curso.id = ?1")
    List<Videoaula> findAllByCurso(Long idCurso);
}
