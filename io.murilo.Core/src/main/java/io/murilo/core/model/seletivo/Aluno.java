package io.murilo.core.model.seletivo;

import io.murilo.core.model.catalogo.Curso;
import io.murilo.core.model.security.Usuario;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Aluno {

    public Aluno() {
    }

    public Aluno(String nome, String sobrenome, Boolean ativo, Boolean vinculadoCurso, Usuario usuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.ativo = ativo;
        this.vinculadoCurso = vinculadoCurso;
        this.usuario = usuario;
    }

    public Aluno(Integer id, String nome, String sobrenome, Boolean ativo, Boolean vinculadoCurso, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.ativo = ativo;
        this.vinculadoCurso = vinculadoCurso;
        this.usuario = usuario;
    }

    public Aluno(String nome, String sobrenome, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String sobrenome;
    private Boolean ativo;
    private Boolean vinculadoCurso;
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getVinculadoCurso() {
        return vinculadoCurso;
    }

    public void setVinculadoCurso(Boolean vinculadoCurso) {
        this.vinculadoCurso = vinculadoCurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //UM USUARIO PODE TER UM ALUNO
    // EVITAR O USO DE VALORES NULO PARA A COLUNA PARA RELACIONAMENTOS OPCIONAIS
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "aluno_vinculo_usuario",
            joinColumns =
                    { @JoinColumn(name = "aluno_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "usuario_id", referencedColumnName = "id") })
    private Usuario usuario;

    //MUITOS ALUNOS PODEM TER MUITOS CURSOS
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Curso> cursos = new HashSet<>();

    public void adicionarCurso(Curso alunoCurso) {
        this.cursos.add(alunoCurso);
        alunoCurso.getAlunos().add(this);
    }
}
