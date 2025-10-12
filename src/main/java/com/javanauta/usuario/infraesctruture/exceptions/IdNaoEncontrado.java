package com.javanauta.usuario.infraesctruture.exceptions;

public class IdNaoEncontrado extends RuntimeException{

    public IdNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNaoEncontrado(String message) {
        super(message);
    }
}
