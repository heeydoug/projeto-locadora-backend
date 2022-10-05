package com.douglas.controller;

import com.douglas.model.Titulo;
import com.douglas.repository.TituloRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titulos")
@AllArgsConstructor
public class TituloController {

    private final TituloRepository tituloRepository;

    @GetMapping
    public List<Titulo> lista() {
        return tituloRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Titulo> procurarPorID(@PathVariable Long id) {
        return tituloRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Titulo criar(@RequestBody Titulo titulo) {
        return tituloRepository.save(titulo);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Titulo> editar(@PathVariable Long id, @RequestBody Titulo titulo){
        return tituloRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(titulo.getNome());
                    recordFound.setAno(titulo.getAno());
                    recordFound.setSinopse(titulo.getSinopse());
                    recordFound.setAtores(titulo.getAtores());
                    recordFound.setDiretor(titulo.getDiretor());
                    recordFound.setClasse(titulo.getClasse());
                    Titulo atualizado = tituloRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        tituloRepository.deleteById(id);
    }
}
