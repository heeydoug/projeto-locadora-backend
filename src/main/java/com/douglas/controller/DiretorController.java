package com.douglas.controller;

import com.douglas.exceptions.RecursoNaoEncontradoException;
import com.douglas.model.Diretor;
import com.douglas.repository.DiretorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
@AllArgsConstructor
public class DiretorController{

    private static final String recurso = "Diretor";
    private final DiretorRepository diretorRepository;

    //Listar
    @GetMapping
    public List<Diretor> lista() {
        return diretorRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Diretor> procurarPorID(@PathVariable Long id) {
        return diretorRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Diretor criar(@RequestBody Diretor diretor) {
        return diretorRepository.save(diretor);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Diretor> editar(@PathVariable Long id, @RequestBody Diretor diretor){
        return diretorRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(diretor.getNome());
                    Diretor atualizado = diretorRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }


    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        diretorRepository.deleteById(id);
    }

}