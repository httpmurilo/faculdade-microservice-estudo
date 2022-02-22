package io.murilo.core.persistence.repository;

import io.murilo.core.model.catalogo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
