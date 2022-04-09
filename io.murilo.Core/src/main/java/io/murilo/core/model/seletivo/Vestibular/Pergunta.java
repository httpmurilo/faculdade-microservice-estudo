package io.murilo.core.model.seletivo.Vestibular;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lista_perguntas")
public class Pergunta {

    public Pergunta(String enunciado, List<Alternativas> listaAlternativas, String respostaCifrada) {
        this.enunciado = enunciado;
        this.listaAlternativas = listaAlternativas;
        this.respostaCifrada = respostaCifrada;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String enunciado;
    private List<Alternativas> listaAlternativas;
    private String respostaCifrada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Alternativas> getListaAlternativas() {
        return listaAlternativas;
    }

    public void setListaAlternativas(List<Alternativas> listaAlternativas) {
        this.listaAlternativas = listaAlternativas;
    }

    public String getRespostaCifrada() {
        return respostaCifrada;
    }

    public void setRespostaCifrada(String respostaCifrada) {
        this.respostaCifrada = respostaCifrada;
    }
}
