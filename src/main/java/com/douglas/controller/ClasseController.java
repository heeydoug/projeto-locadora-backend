package com.douglas.controller;

import com.douglas.exceptions.RecursoNaoEncontradoException;
import com.douglas.model.Ator;
import com.douglas.model.Classe;
import com.douglas.repository.ClasseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class ClasseController {

    private static final String recurso = "Classe";
    private final ClasseRepository classeRepository;

    //Listar
    @GetMapping
    public List<Classe> lista() {
        return classeRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Classe> procurarPorID(@PathVariable Long id) {
        return classeRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Classe criar(@RequestBody Classe classe) {
        return classeRepository.save(classe);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Classe> editar(@PathVariable Long id, @RequestBody Classe classe){
        return classeRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(classe.getNome());
                    recordFound.setData(classe.getData());
                    recordFound.setValor(classe.getValor());
                    Classe atualizado = classeRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }


    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
       classeRepository.deleteById(id);
    }
}
