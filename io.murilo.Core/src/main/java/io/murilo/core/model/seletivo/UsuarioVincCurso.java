package io.murilo.core.model.seletivo;

import javax.persistence.*;

@Entity
@Table
public class UsuarioVincCurso {

    //CLASSE COM RELACIONAMENTOS NÃO GERENCIADO POR JPA, RELACIONAMENTOS GERENCIADOS PELA APP
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer usuario_id;
    private Integer curso_id;
    private Integer aluno_id;
    @Enumerated(EnumType.STRING)
    private StatusSeletivo statusSeletivo;

    public Integer getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Integer aluno_id) {
        this.aluno_id = aluno_id;
    }

    public StatusSeletivo getStatusSeletivo() {
        return statusSeletivo;
    }

    public void setStatusSeletivo(StatusSeletivo statusSeletivo) {
        this.statusSeletivo = statusSeletivo;
    }

    public UsuarioVincCurso(Integer usuario_id, Integer curso_id, Integer aluno_id, StatusSeletivo statusSeletivo) {
        this.usuario_id = usuario_id;
        this.curso_id = curso_id;
        this.aluno_id = aluno_id;
        this.statusSeletivo = statusSeletivo;
    }

    public UsuarioVincCurso() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }
}
