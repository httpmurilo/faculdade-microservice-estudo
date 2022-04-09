package io.murilo.core.dto.curso.seletivo;

public class NovoUsuarioSeletivoDto {
   private String nome;
   private String sobrenome;
   private String email;
   private String senha;

    public String getSenha() {
        return senha;
    }
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }
}