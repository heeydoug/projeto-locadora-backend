package com.douglas.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String recurso) {
        super(recurso + " não encontrado(a)");
    }

}
