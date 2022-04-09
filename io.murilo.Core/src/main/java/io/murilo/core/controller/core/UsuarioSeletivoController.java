package io.murilo.core.controller.core;

import io.murilo.core.dto.curso.seletivo.NovoUsuarioSeletivoDto;
import io.murilo.core.model.security.Usuario;
import io.murilo.core.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarioSeletivo")
public class UsuarioSeletivoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/")
    public ResponseEntity<Usuario> criarUsuarioPrimeiroAcesso(@RequestBody NovoUsuarioSeletivoDto dto) {
        Usuario usuario = usuarioRepository.save(new Usuario(dto.getEmail(), dto.getSenha(), dto.getNome(), dto.getSobrenome(), false));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
