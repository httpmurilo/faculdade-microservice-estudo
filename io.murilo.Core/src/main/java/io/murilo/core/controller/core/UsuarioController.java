package io.murilo.core.controller.core;

import com.netflix.discovery.converters.Auto;
import io.murilo.core.exceptions.GenericExceptionError;
import io.murilo.core.model.catalogo.Curso;
import io.murilo.core.model.security.Usuario;
import io.murilo.core.model.seletivo.UsuarioVincCurso;
import io.murilo.core.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> all = repository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        Optional<Usuario> usuario = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new GenericExceptionError("Usuario não encontrado")));
        return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> postUsuario(@RequestParam Usuario usuario){
        Usuario save = repository.save(usuario);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUser(@PathVariable Integer id, @RequestParam Usuario usuario) {
        Usuario usuario1 = repository.findById(id)
                .map(usuarioExistente -> {
                    usuario.setId(usuarioExistente.getId());
                    repository.save(usuario);
                    return usuarioExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel editar o usuario"));
        return ResponseEntity.ok(usuario1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Usuario deletado com sucesso");
    }
}
