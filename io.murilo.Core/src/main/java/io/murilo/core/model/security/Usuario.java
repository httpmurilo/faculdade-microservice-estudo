package io.murilo.core.model.security;

import io.murilo.core.model.seletivo.Aluno;

import javax.persistence.*;

@Entity
@Table
public class Usuario {

    public Usuario() {
    }

    public Usuario(Integer id, String login, String senha, boolean admin) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    public Usuario(String login, String senha, boolean admin) {
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String login;
    private String senha;

    //UM USUARIO PODE TER UM ALUNO
    // EVITAR O USO DE VALORES NULO PARA A COLUNA PARA RELACIONAMENTOS OPCIONAIS
    @OneToOne(mappedBy = "usuario")
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }



    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private boolean admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
