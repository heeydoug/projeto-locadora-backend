package com.douglas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

import javax.persistence.Entity;

import javax.persistence.*;

@Data
@Entity //Entidade para mapeamento de banco de dados
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column
    private Integer numeroInscricao;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column(length = 15, nullable = false)
    private String sexo;

    @Column(length = 5, nullable = false)
    private String estahAtivo;


}
