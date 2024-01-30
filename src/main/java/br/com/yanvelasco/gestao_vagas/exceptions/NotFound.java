package br.com.yanvelasco.gestao_vagas.exceptions;

public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
