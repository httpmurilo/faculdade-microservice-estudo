package io.murilo.core.model.seletivo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class AlunoCurso {

    //MUITOS ALUNOS PODEM TER MUITOS CURSOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @ManyToMany(mappedBy = "alunoVincCurso")
    private Set<Aluno> alunos = new HashSet<>();

}
