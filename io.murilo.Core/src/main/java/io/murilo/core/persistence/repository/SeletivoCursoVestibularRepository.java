package io.murilo.core.persistence.repository;

import io.murilo.core.model.seletivo.Vestibular.CursoVestibular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeletivoCursoVestibularRepository extends JpaRepository<CursoVestibular, Integer> {
}
