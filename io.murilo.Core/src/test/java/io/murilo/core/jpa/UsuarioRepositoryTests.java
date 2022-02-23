package io.murilo.core.jpa;

import io.murilo.core.model.security.Usuario;
import io.murilo.core.persistence.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void Test_criarNovoUsuario() {
        var usuario = new Usuario("murilo","1020", true);
        var usuarioSalvo = repository.save(usuario);
        assertThat(usuarioSalvo.getId()).isGreaterThan(0);
        assertThat(usuarioSalvo.getLogin()).isNotEmpty();
    }

    @Test
    public void Test_editarUsuarioExistente() {
        var usuario = new Usuario(1,"murilo editado","1020", true);
        var usuarioSalvo = repository.save(usuario);
        assertThat(usuarioSalvo.getId()).isEqualTo(0);
        assertThat(usuarioSalvo.getLogin()).isEqualTo("murilo editado");
    }

    @Test
    public void test_deletarUsuarioExistente() {
        repository.deleteById(retornarIdUsuarioCriado());
    }

    @Test
    public void Test_obterTodosUsuariosExistentes() {
        Iterable<Usuario> usuarios = repository.findAll();
        assertThat(usuarios).asList();
        usuarios.forEach(item -> {
            assertThat(item).isNotNull();
        });
    }

    @Test
    public void Test_obterUsuarioPorId() {
        Optional<Usuario> usuario = repository.findById(retornarIdUsuarioCriado());
        assertThat(usuario.get()).isNotNull();
    }

    @Test
    public void Test_obterPorIdInvalidaVaiGerarNoSuchElement() {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> repository.findById(13231231).get());
        assertEquals("No value present", exception.getMessage());
    }

    private Integer retornarIdUsuarioCriado() {
        var usuario = new Usuario("murilo","1020", true);
        var usuarioSalvo = repository.save(usuario);
        return usuarioSalvo.getId();
    }
}
