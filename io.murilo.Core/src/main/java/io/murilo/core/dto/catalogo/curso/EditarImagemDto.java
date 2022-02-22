package io.murilo.core.dto.catalogo.curso;

public class EditarImagemDto {

    public EditarImagemDto() {}

    public EditarImagemDto(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    private String caminhoImagem;
}
