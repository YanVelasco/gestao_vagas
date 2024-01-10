package br.com.yanvelasco.gestao_vagas.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message) {
        super(message);
    }
}