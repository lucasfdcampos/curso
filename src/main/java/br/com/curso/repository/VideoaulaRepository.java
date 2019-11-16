package br.com.curso.repository;

import br.com.curso.model.Videoaula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoaulaRepository extends JpaRepository<Videoaula, Long> {

}
