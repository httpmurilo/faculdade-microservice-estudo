package io.murilo.core.model.seletivo.Vestibular;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "curso_perguntas")
public class CursoVestibular {

    public CursoVestibular() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCurso;

    public Integer getId() {
        return id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public CursoVestibular(String nomeCurso, List<Pergunta> perguntas) {
        this.nomeCurso = nomeCurso;
        this.perguntas = perguntas;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    private List<Pergunta> perguntas;
}
