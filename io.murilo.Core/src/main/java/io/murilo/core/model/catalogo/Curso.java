package io.murilo.core.model.catalogo;

import io.murilo.core.model.seletivo.Aluno;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Curso {

    public Curso() {}

    public Curso(Integer id, String nome, String descricao, String areaDeAtuacao, Integer quantidadeTurma,
                 Integer quantidadeAluno, double valorMensalidade, double valorTotal, String nomeImagem,
                 TipoCurso tipoCurso) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.areaDeAtuacao = areaDeAtuacao;
        this.quantidadeTurma = quantidadeTurma;
        this.quantidadeAluno = quantidadeAluno;
        this.valorMensalidade = valorMensalidade;
        this.valorTotal = valorTotal;
        this.nomeImagem = nomeImagem;
        this.tipoCurso = tipoCurso;
    }

    //TEST
    public Curso(String nome, String descricao, String areaDeAtuacao, Integer quantidadeTurma, Integer quantidadeAluno,
                 double valorMensalidade, double valorTotal, String nomeImagem, TipoCurso tipoCurso) {
        this.nome = nome;
        this.descricao = descricao;
        this.areaDeAtuacao = areaDeAtuacao;
        this.quantidadeTurma = quantidadeTurma;
        this.quantidadeAluno = quantidadeAluno;
        this.valorMensalidade = valorMensalidade;
        this.valorTotal = valorTotal;
        this.nomeImagem = nomeImagem;
        this.tipoCurso = tipoCurso;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;

    //long text mysql
    @Lob
    private String descricao;
    private String areaDeAtuacao;
    private Integer quantidadeTurma;
    private Integer quantidadeAluno;
    private double valorMensalidade;
    private double valorTotal;
    private String nomeImagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_curso")
    private TipoCurso tipoCurso;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="tb_vinc_usuario_curso",
            joinColumns={@JoinColumn(name="curso_id")},
            inverseJoinColumns={@JoinColumn(name="aluno_id")})
    private Set<Aluno> alunos = new HashSet<>();

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public Integer getQuantidadeTurma() {
        return quantidadeTurma;
    }

    public void setQuantidadeTurma(Integer quantidadeTurma) {
        this.quantidadeTurma = quantidadeTurma;
    }

    public Integer getQuantidadeAluno() {
        return quantidadeAluno;
    }

    public void setQuantidadeAluno(Integer quantidadeAluno) {
        this.quantidadeAluno = quantidadeAluno;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
