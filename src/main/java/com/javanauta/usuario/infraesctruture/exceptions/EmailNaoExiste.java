package com.javanauta.usuario.infraesctruture.exceptions;

public class EmailNaoExiste extends RuntimeException{

    public EmailNaoExiste(String message) {
        super(message);
    }

    public EmailNaoExiste(String message, Throwable cause) {
        super(message, cause);
    }
}
