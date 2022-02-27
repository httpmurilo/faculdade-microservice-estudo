package io.murilo.core.dto.curso.seletivo;

import io.murilo.core.model.seletivo.StatusSeletivo;

public class UsuarioCursoDto {

    private Integer usuarioId;
    private Integer cursoId;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public StatusSeletivo getStatusSeletivo() {
        return statusSeletivo;
    }

    public void setStatusSeletivo(StatusSeletivo statusSeletivo) {
        this.statusSeletivo = statusSeletivo;
    }

    private StatusSeletivo statusSeletivo;
}
