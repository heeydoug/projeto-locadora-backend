package com.douglas.controller;

import com.douglas.model.Cliente;
import com.douglas.repository.ClienteRepository;
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
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    //Listar
    @GetMapping
    public List<Cliente> lista() {
        return clienteRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> procurarPorID(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNumeroInscricao(cliente.getNumeroInscricao());
                    recordFound.setNome(cliente.getNome());
                    recordFound.setDataNascimento(cliente.getDataNascimento());
                    recordFound.setSexo(cliente.getSexo());
                    recordFound.setEstahAtivo(cliente.getEstahAtivo());

                    Cliente atualizado = clienteRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        clienteRepository.deleteById(id);
    }

}
