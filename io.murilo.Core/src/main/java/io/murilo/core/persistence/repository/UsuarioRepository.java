package io.murilo.core.persistence.repository;

import io.murilo.core.model.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
