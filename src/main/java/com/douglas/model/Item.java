package com.douglas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity //Entidade para mapeamento de banco de dados
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(nullable = false)
    private Integer numserie;

    @Column
    private LocalDate dtaquisicao;

    @Column(length = 200, nullable = false)
    private String tipoItem;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;

    public Item() {

    }

    public Integer getNumserie() {
        return numserie;
    }
}
