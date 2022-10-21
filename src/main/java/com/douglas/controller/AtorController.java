package com.douglas.controller;

import com.douglas.exceptions.RecursoNaoEncontradoException;
import com.douglas.model.Ator;
import com.douglas.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
@AllArgsConstructor
public class AtorController{

    private static final String recurso = "Ator";
    private final AtorRepository atorRepository;

    //Listar
    @GetMapping
    public List<Ator> lista() {
        return atorRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Ator> procurarPorID(@PathVariable Long id) {
        return atorRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ator criar(@RequestBody Ator ator) {
        return atorRepository.save(ator);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Ator> editar(@PathVariable Long id, @RequestBody Ator ator){
        return atorRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(ator.getNome());
                    Ator atualizado = atorRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }


    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        atorRepository.deleteById(id);
    }

}