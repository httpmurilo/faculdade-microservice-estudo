package io.murilo.core.controller.catalogo;

import io.murilo.core.dto.catalogo.curso.EditarImagemDto;
import io.murilo.core.exceptions.GenericExceptionError;
import io.murilo.core.fileStorage.FileStorageService;
import io.murilo.core.model.catalogo.Curso;
import io.murilo.core.persistence.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/")
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> all = repository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Integer id) {
        Optional<Curso> curso = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new GenericExceptionError("Usuario não encontrado")));
        return new ResponseEntity<>(curso.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Curso> postCurso(@RequestBody Curso curso, @RequestParam("file") MultipartFile file) {

        persistirImagem(curso, file);
        Curso savedCurso = repository.save(curso);
        return  ResponseEntity.ok(savedCurso);
    }

    protected void persistirImagem(Curso curso, MultipartFile file) {
        if(!file.isEmpty()) {
            var fileName = file.getOriginalFilename();
            curso.setNomeImagem(fileName);
            fileStorageService.save(file);
        } else {
            curso.setNomeImagem(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> putCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        Curso retornoCurso = repository.findById(id)
                .map(cursoExistente -> {
                    curso.setId(cursoExistente.getId());
                    repository.save(curso);
                    return cursoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        return  ResponseEntity.ok(retornoCurso);
    }

    public ResponseEntity<String> putEnderecoImagem(@PathVariable Integer id, EditarImagemDto dto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Curso deletado com sucesso");
    }
}
