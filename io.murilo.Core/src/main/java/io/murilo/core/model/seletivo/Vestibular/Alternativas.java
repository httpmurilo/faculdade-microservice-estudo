package io.murilo.core.model.seletivo.Vestibular;

public class Alternativas {
    private String opcao;
    private String texto;

    public Alternativas(String opcao, String texto) {
        this.opcao = opcao;
        this.texto = texto;
    }

    public String getOpcao() {
        return opcao;
    }

    public String getTexto() {
        return texto;
    }
}
