package io.murilo.core.model.seletivo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class UsuarioVincCurso {

    //CLASSE NÃO GERENCIADA POR JPA, RELACIONAMENTOS GERENCIADOS PELA APP
    private Integer id;
    private Integer usuario_id;
    private Integer curso_id;

    public UsuarioVincCurso() {}
    public UsuarioVincCurso(Integer id, Integer usuario_id, Integer curso_id) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.curso_id = curso_id;
    }

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
