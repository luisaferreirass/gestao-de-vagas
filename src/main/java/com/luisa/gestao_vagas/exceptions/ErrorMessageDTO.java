package com.luisa.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //Criação de construtor com argumentos
public class ErrorMessageDTO {
    // Data transfer to object
    // Classe da mensagem de erro do formato que a gente quer
    private String message;
    private String field;
}
