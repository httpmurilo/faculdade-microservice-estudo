package io.murilo.core.jpa;

import io.murilo.core.model.catalogo.Curso;
import io.murilo.core.model.catalogo.TipoCurso;
import io.murilo.core.persistence.repository.CursoRepository;
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
@Rollback(value = true)
public class CursoRepositoryTests {

    @Autowired
    private CursoRepository repository;
    @Test
    public void Test_criarNovoCurso() {
        var curso = new Curso("Licenciatura em Filosofia", "A Filosofia procura estudar a essência da humanidade e a realidade em que vivemos. Refletir sobre o sentido das coisas e apresentar diferentes visões da realidade são atividades desempenhadas por profissionais desta área. Os filósofos são estudiosos e pensadores.",
                "escolas", 5, 50, 250.00, 10.000, "filosofia.png", TipoCurso.GRADUCAO);

        var cursoSalvo = repository.save(curso);
        assertThat(cursoSalvo.getId()).isGreaterThan(0);
        assertThat(cursoSalvo.getNome()).isNotEmpty();
    }

    @Test
    public void Test_editarCursoExistente() {
        var curso = new Curso(1,"Licenciatura em Filosofia Editado", "A Filosofia procura estudar a essência da humanidade e a realidade em que vivemos. Refletir sobre o sentido das coisas e apresentar diferentes visões da realidade são atividades desempenhadas por profissionais desta área. Os filósofos são estudiosos e pensadores.",
                "escolas", 5, 50, 250.00, 10.000, "filosofia.png", TipoCurso.GRADUCAO);

        var cursoSalvo = repository.save(curso);
        assertThat(cursoSalvo.getId()).isGreaterThan(0);
        assertThat(cursoSalvo.getNome()).isEqualTo("Licenciatura em Filosofia Editado");
    }

    @Test
    public void Test_deletarCursoExistente() {
        repository.deleteById(retornarIdUsuarioCriado());
    }

    @Test
    public void Test_obterTodosCursoExistente() {
        Iterable<Curso> cursos = repository.findAll();
        assertThat(cursos).asList();
        cursos.forEach(item -> {
            assertThat(item).isNotNull();
        });
    }

    @Test
    public void Test_obterCursoPorId() {
        Optional<Curso> curso = repository.findById(retornarIdUsuarioCriado());
        assertThat(curso.get()).isNotNull();
    }

    @Test
    public void Test_obterPorIdInvalidaVaiGerarNoSuchElement() {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> repository.findById(13231231).get());
        assertEquals("No value present", exception.getMessage());
    }

    private Integer retornarIdUsuarioCriado() {
        var curso = new Curso("Licenciatura em Filosofia", "A Filosofia procura estudar a essência da humanidade e a realidade em que vivemos. Refletir sobre o sentido das coisas e apresentar diferentes visões da realidade são atividades desempenhadas por profissionais desta área. Os filósofos são estudiosos e pensadores.",
                "escolas", 5, 50, 250.00, 10.000, "filosofia.png", TipoCurso.GRADUCAO);

        var cursoSalvo = repository.save(curso);
        return cursoSalvo.getId();
    }
}
