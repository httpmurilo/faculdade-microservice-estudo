package io.murilo.core.controller.seletivo;

import io.murilo.core.model.seletivo.Vestibular.CursoVestibular;
import io.murilo.core.persistence.repository.SeletivoCursoVestibularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vestibular")
@RestController
public class VestibularController {


    @Autowired
    private SeletivoCursoVestibularRepository seletivoCursoVestibularRepository;

    @PostMapping("/")
    public ResponseEntity<CursoVestibular> criarPerguntar(@RequestBody CursoVestibular cursoVestibular) {
        CursoVestibular perguntaSalva = seletivoCursoVestibularRepository.save(cursoVestibular);
        return new ResponseEntity<>(perguntaSalva, HttpStatus.OK);
    }
}
