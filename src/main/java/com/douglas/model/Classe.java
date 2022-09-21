package com.douglas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

import javax.persistence.*;;

@Data
@Entity //Entidade para mapeamento de banco de dados
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column
    private Integer valor;

    @Column
    private LocalDate data;
}
