package com.douglas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity //Entidade para mapeamento de banco de dados
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private LocalDate ano;

    @Column(length = 200, nullable = false)
    private String sinopse;

    @NotNull
    @Column
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;

    @ManyToMany
    @NotNull
    @JoinTable(name="ator_titulo", joinColumns= {@JoinColumn(name="idTitulo")}, inverseJoinColumns= {@JoinColumn(name="idAtor")})
    private List<Ator> atores;

    public Titulo() {

    }
}
