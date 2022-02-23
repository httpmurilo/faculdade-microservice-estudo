package io.murilo.core.persistence.repository;

import io.murilo.core.model.seletivo.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
