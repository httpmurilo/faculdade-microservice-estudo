package io.murilo.core.persistence.repository;

import io.murilo.core.model.seletivo.AlunoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoCursoRepository extends JpaRepository<AlunoCurso, Integer> {
}
