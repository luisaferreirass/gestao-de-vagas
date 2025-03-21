package com.luisa.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuário já existe");
        // Criamos uma nova exceção que vai estender a runtime (que não precisa ser tratada)
        // para usarmos essa invés de lançar uma Exception logo no controller
    }
}
