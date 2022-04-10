package io.murilo.core.controller.seletivo;

import io.murilo.core.dto.curso.seletivo.UsuarioCursoDto;
import io.murilo.core.model.security.Usuario;
import io.murilo.core.model.seletivo.Aluno;
import io.murilo.core.model.seletivo.StatusSeletivo;
import io.murilo.core.model.seletivo.UsuarioVincCurso;
import io.murilo.core.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/seletivo")
public class SeletivoController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioVIncCursoRepository usuarioVIncCursoRepository;

    @PostMapping("/")
    public ResponseEntity<String> criarNovoSeletivoComUser(@RequestBody UsuarioCursoDto usuarioCursoDto) {

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

        var usuarioVincCurso =new UsuarioVincCurso(usuarioCadastrado.getId(), curso.getId(), alunoParaSalvamento.getId(), StatusSeletivo.ANDAMENTO);
        usuarioVIncCursoRepository.save(usuarioVincCurso);

        return ResponseEntity.ok("Criado com sucesso");
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioVincCurso> obterSeletivoPorAluno(@PathVariable Integer id) {
        Optional<UsuarioVincCurso> usuarioVinc = usuarioVIncCursoRepository.findById(id);
        return new ResponseEntity<>(usuarioVinc.get(), HttpStatus.OK);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioVincCurso atualizarStatusParaEncerrado(@PathVariable Integer id) {
        return usuarioVIncCursoRepository.findById(id)
                .map(vinculoExistente -> {
                    vinculoExistente.setStatusSeletivo(StatusSeletivo.FINALIZADO);
                    usuarioVIncCursoRepository.save(vinculoExistente);
                    return vinculoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao editar o status do seletivo"));
    }
}
