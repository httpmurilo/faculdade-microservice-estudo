package io.murilo.core.controller.seletivo;

import io.murilo.core.dto.curso.seletivo.UsuarioCursoDto;
import io.murilo.core.model.seletivo.Aluno;
import io.murilo.core.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/seletivo")
public class SeletivoController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/")
    public ResponseEntity<String> criarNovoSeletivoComUserCadastrado(@RequestBody UsuarioCursoDto usuarioCursoDto) {

       var usuarioCadastrado = usuarioRepository.findById(usuarioCursoDto.getUsuarioId()).get();

        if(ObjectUtils.isEmpty(usuarioCadastrado)) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuario encontrado com a id informada");
        }

        var alunoParaSalvamento = new Aluno(usuarioCadastrado.getNome(), usuarioCadastrado.getSobrenome(), true, true, usuarioCadastrado);
        var curso = cursoRepository.findById(usuarioCursoDto.getCursoId()).get();

        if(ObjectUtils.isEmpty(curso)) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum curso encontrado com a id informada");
        }

        alunoParaSalvamento.adicionarCurso(curso);
        alunoRepository.save(alunoParaSalvamento);

        return ResponseEntity.ok("Criado com sucesso");

    }
}
