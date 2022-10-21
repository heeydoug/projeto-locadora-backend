package com.douglas.controller;

import com.douglas.exceptions.RecursoNaoEncontradoException;
import com.douglas.model.Item;
import com.douglas.model.Titulo;
import com.douglas.repository.ItemRepository;
import com.douglas.repository.TituloRepository;
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
@RequestMapping("/api/itens")
@AllArgsConstructor
public class ItemController {

    private static final String recurso = "Item";
    private final ItemRepository itemRepository;

    @GetMapping
    public List<Item> lista() {
        return itemRepository.findAll();
    }

    //Procurar o ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> procurarPorID(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Criar
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item criar(@RequestBody Item item) {
        return itemRepository.save(item);

    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Item> editar(@PathVariable Long id, @RequestBody Item item){
        return itemRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNumserie(item.getNumserie());
                    recordFound.setDtaquisicao(item.getDtaquisicao());
                    recordFound.setTipoItem(item.getTipoItem());
                    recordFound.setTitulo(item.getTitulo());
                    Item atualizado = itemRepository.save(recordFound);
                    return ResponseEntity.ok().body(atualizado);
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException(recurso));
    }

    //Deletar
    @DeleteMapping()
    public void deletar(@RequestParam Long id){
        itemRepository.deleteById(id);
    }
}
