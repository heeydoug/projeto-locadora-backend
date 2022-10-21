package com.douglas.controller;


import com.douglas.exceptions.RecursoNaoEncontradoException;
import com.douglas.model.Cliente;
import com.douglas.model.Socio;
import com.douglas.repository.SocioRepository;
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
@RequestMapping("/api/socios")
@AllArgsConstructor
public class SocioController {

    private static final String recurso = "Sócio";
    private final SocioRepository socioRepository;

    //Listar
    @GetMapping
    public List<Socio> lista() {
        return socioRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Socio> procurarPorID(@PathVariable Long id) {
        return socioRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Socio criar(@RequestBody Socio socio) {
        return socioRepository.save(socio);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Socio> editar(@PathVariable Long id, @RequestBody Socio socio){
        return socioRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNumeroInscricao(socio.getNumeroInscricao());
                    recordFound.setNome(socio.getNome());
                    recordFound.setCpf(socio.getCpf());
                    recordFound.setDataNascimento(socio.getDataNascimento());
                    recordFound.setEndereco(socio.getEndereco());
                    recordFound.setTelefone(socio.getTelefone());
                    recordFound.setSexo(socio.getSexo());
                    recordFound.setDependentes(socio.getDependentes());
                    recordFound.setEstahAtivo(socio.getEstahAtivo());

                    Socio atualizado = socioRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }


    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        socioRepository.deleteById(id);
    }
}
